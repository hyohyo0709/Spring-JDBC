package part02_mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// http://localhost:8090/myapp/list.do
// http://localhost:8090/myapp/insert.do

@Controller
public class MemController {

	@Autowired
	private MemDAO dao;
	
	public MemController() {
		
	}
	
	public void setDao(MemDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value = "/list.do")
	public ModelAndView process(ModelAndView mav) {
	mav.addObject("list", dao.list());
	mav.setViewName("part02/list");
		return mav;
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.GET)
	public String insertForm() {
		return "part02/insert";
	}
	
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String insertData(MemDTO dto) {
		dao.insertMethod(dto);
		
		return "redirect:/list.do";
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public ModelAndView updateForm(int num, ModelAndView mav) {
		mav.addObject("dto",dao.one(num));
		mav.setViewName("part02/update");
		return mav;
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String updateData(MemDTO dto) {
		dao.updateMethod(dto);
		return "redirect:/list.do";
	}
	
	@RequestMapping(value = "/delete.do")
	public String deleteData(int num) {
		
		dao.deleteMethod(num);
		
		return "redirect:/list.do";
	}
}
