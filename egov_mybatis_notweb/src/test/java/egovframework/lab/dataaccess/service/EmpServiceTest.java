package egovframework.lab.dataaccess.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/context-*" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class EmpServiceTest {
	
	// TODO [Step 4-1] EmpServiceTest 작성
    
    @Resource(name = "dataSource")
    DataSource dataSource;

    @Resource(name = "empService")
    EmpService empService;
    
    @Before
    public void onSetUp() throws Exception {
        // 편의상 각 테스트 메서드 수행 전에
    	// 외부의 스크립트 파일(sample_schema_hsql.sql)로 DB를 초기화하도록 설정
    	JdbcTestUtils.executeSqlScript(new JdbcTemplate(dataSource), new ClassPathResource("META-INF/testdata/sample_schema_hsql.sql"), true);
    }
    
    /** 사원정보 생성 */
    public EmpVO makeVO() throws ParseException {
        EmpVO vo = new EmpVO();

        // empNo는 Biz. 서비스 내에서 IDGeneration Service 에 의해 key를 생성하고 설정
        vo.setEmpName("홍길동");
        vo.setJob("개발자");
        vo.setMgr(new BigDecimal(7902));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        vo.setHireDate(sdf.parse("2009-07-09"));
        vo.setSal(new BigDecimal(1000));
        vo.setComm(new BigDecimal(0));
        vo.setDeptNo(new BigDecimal(20));

        return vo;
    }

    public void checkResult(EmpVO vo, EmpVO resultVO) {
        assertNotNull(resultVO);
        assertEquals(vo.getEmpNo(), resultVO.getEmpNo());
        assertEquals(vo.getEmpName(), resultVO.getEmpName());
        assertEquals(vo.getJob(), resultVO.getJob());
        assertEquals(vo.getMgr(), resultVO.getMgr());
        assertEquals(vo.getHireDate(), resultVO.getHireDate());
        assertEquals(vo.getSal(), resultVO.getSal());
        assertEquals(vo.getComm(), resultVO.getComm());
        assertEquals(vo.getDeptNo(), resultVO.getDeptNo());
    }

    /** 사원정보 입력 */
    @Test
    public void testInsertEmp() throws Exception {
        EmpVO vo = makeVO();

        // insert
        BigDecimal empNo = empService.insertEmp(vo);
        vo.setEmpNo(empNo);

        // select
        EmpVO resultVO = empService.selectEmp(vo);

        // check
        checkResult(vo, resultVO);
    }

    /** 사원정보 수정 */
    @Test
    public void testUpdateEmp() throws Exception {
        EmpVO vo = makeVO();

        // insert
        BigDecimal empNo = empService.insertEmp(vo);
        vo.setEmpNo(empNo);

        // data change
        vo.setEmpName("홍길순");
        vo.setJob("설계자");

        // update
        empService.updateEmp(vo);

        // select
        EmpVO resultVO = empService.selectEmp(vo);

        // check
        checkResult(vo, resultVO);
    }

    /** 사원정보 삭제 */
    @Test
    public void testDeleteEmp() throws Exception {
        EmpVO vo = makeVO();

        // insert
        BigDecimal empNo = empService.insertEmp(vo);
        vo.setEmpNo(empNo);

        // delete
        empService.deleteEmp(vo);

        // select
        try {
            @SuppressWarnings("unused")
            EmpVO resultVO = empService.selectEmp(vo);
            fail("EgovBizException 이 발생해야 합니다.");
        } catch (Exception e) {
            assertNotNull(e);
            // 여기서는 비지니스 단에서 명시적으로 exception 처리하였음
            // EgovAbstractServiceImpl 을 extends 하고
            // processException("info.nodata.msg"); 과 같이 메서드 콜 형태로 처리
            assertTrue(e instanceof EgovBizException);
            assertEquals("info.nodata.msg", ((EgovBizException) e).getMessageKey());
            assertEquals("해당 데이터가 없습니다.", e.getMessage());
        }
    }

    /** 사원정보 목록조회 */
    @Test
    public void testSelectEmpList() throws Exception {
        EmpVO vo = makeVO();

        // insert
        BigDecimal empNo = empService.insertEmp(vo);
        vo.setEmpNo(empNo);

        // 검색조건으로 empNo 설정
        EmpVO searchVO = new EmpVO();
        searchVO.setEmpNo(vo.getEmpNo());

        // selectList
        List<EmpVO> resultList = empService.selectEmpList(searchVO);

        // empNo 조건에 대한 결과는 1건일 것임
        assertNotNull(resultList);
        assertTrue(resultList.size() > 0);
        assertEquals(1, resultList.size());
        checkResult(vo, resultList.get(0));

        // 검색조건으로 empName 설정 - '%' || #{empName} || '%'
        EmpVO searchVO2 = new EmpVO();
        searchVO2.setEmpName(""); // '%' || '' || '%' // --> '%%'

        // selectList
        List<EmpVO> resultList2 = empService.selectEmpList(searchVO2);

        // like 조건에 대한 결과는 1건 이상일 것임
        assertNotNull(resultList2);
        assertTrue(resultList2.size() > 0);
    }

    /**
     *  하나의 사원과 해당 사원이 속한 부서정보를 조회
     * */
    @Test
    public void testSelectEmpWithDept() throws Exception {
        EmpVO vo = makeVO();

        // insert
        BigDecimal empNo = empService.insertEmp(vo);
        vo.setEmpNo(empNo);

        // select - empNo(8000)'s dept
        EmpWithDeptVO resultVO = empService.selectEmpWithDept(vo);

        // check - empNo(8000)'s emp data
        checkResult(vo, resultVO);
        
        // check empNo(8000)'s dept data
        assertEquals(vo.getDeptNo(), resultVO.getDeptNo());
        assertEquals("RESEARCH", resultVO.getDeptName());
        assertEquals("DALLAS", resultVO.getLoc());
    }
    
    /**
     *  하나의 사원과 해당 사원이 속한 부서정보를 조회
     *  dept data를 담기 위한 Result Object는 Mapper XML 설정에서 <association>으로 표현
     * */
    @Test
    public void testSelectEmpIncludesDept() throws Exception {
        EmpVO vo = makeVO();

        // insert
        BigDecimal empNo = empService.insertEmp(vo);
        vo.setEmpNo(empNo);

        // select - empNo(8000)'s dept
        EmpIncludesDept resultVO = empService.selectEmpIncludesDept(vo);

        // check - empNo(8000)'s emp data
        checkResult(vo, resultVO);
        
        // check empNo(8000)'s dept data
        assertEquals(vo.getDeptNo(), resultVO.getDeptVO().getDeptNo());
        assertEquals("RESEARCH", resultVO.getDeptVO().getDeptName());
        assertEquals("DALLAS", resultVO.getDeptVO().getLoc());
    }

    @Test
    public void testSelectUsingCache() throws Exception {
        EmpVO vo = makeVO();
        
        // insert
        BigDecimal empNo = empService.insertEmp(vo);
        vo.setEmpNo(empNo); // 검색조건으로 empNo 활용
        
        // select - 구문결과를 캐시하는 select 첫실행
        EmpVO resultVO = empService.selectEmpUsingCache(vo);
        checkResult(vo, resultVO);
    	
        // select - 캐시설정이 안된 select 실행
        EmpVO resultVO2 = empService.selectEmp(vo);
        checkResult(vo, resultVO2);
        
        // resultVO와 resultVO2 비교 - 서로 다른 인스턴스 리턴
        assertNotSame(resultVO, resultVO2);
    	
        // select - 캐시설정이 된 select 재실행
        EmpVO resultVO3 = empService.selectEmpUsingCache(vo);
        checkResult(vo, resultVO3);
        
        // resultVO와 resultVO3 비교 - 캐시된 같은 인스턴스 리턴
        assertEquals(resultVO, resultVO3);
        assertSame(resultVO, resultVO3);
        

        // insert
        EmpVO vo2 = makeVO();
        vo2.setEmpName("홍길돈");
        
        BigDecimal empNo2 = empService.insertEmp(vo2);
        vo2.setEmpNo(empNo2);
        
        // select - 홍길돈 생성확인
        EmpVO resultVO4 = empService.selectEmp(vo2);
        checkResult(vo2, resultVO4);
        
        // insert - flush cache test
        EmpVO resultVO5 = empService.selectEmpUsingCache(vo);
        checkResult(vo, resultVO5);
        
        // resultVO와 resultVO5 비교 - insert 호출로 cache가 flush되어 다른 인스턴스 리턴
        assertNotSame(resultVO, resultVO5);

    }
}
