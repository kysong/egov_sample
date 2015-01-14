package egovframework.lab.ajax.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.lab.com.view.AjaxSimpleView;


@Controller
public class AjaxSimpleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxSimpleController.class);

	private String[] keywords = { "가", "가나", "가나다", "가나다라마", "나", "나다", "나다라마" };

	@SuppressWarnings("unchecked")
	private List<String> search(String keyword) {
		if (keyword == null || keyword.equals(""))
			return java.util.Collections.EMPTY_LIST;
		keyword = keyword.toUpperCase();
		List<String> result = new ArrayList<String>(8);
		for (int i = 0; i < keywords.length; i++) {
			if (((String) keywords[i]).startsWith(keyword)) {
				result.add(keywords[i]);
			}
		}
		return result;
	}

	//TODO [Step 2-1-1] simpleAjax 메소드 완성하기 
	@RequestMapping(value = "/autocomplete.do")
	public ModelAndView simpleAjax(@RequestParam("keyword") String keyword) {

		ModelAndView mav = new ModelAndView(new AjaxSimpleView());

		List<?> keywordList = search(keyword);
		StringBuffer sb = new StringBuffer();
		sb.append(keywordList.size());
		sb.append("|");
		for (int i = 0; i < keywordList.size(); i++) {
			String key = (String) keywordList.get(i);
			sb.append(key);
			if (i < keywordList.size() - 1) {
				sb.append(",");
			}
		}
		
		LOGGER.debug(sb.toString());
		mav.addObject("ajaxSimple", sb.toString());

		return mav;
	}
}
