package part01_template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// http://localhost:8090/myapp/list.htm
// http://localhost:8090/myapp/insert.htm

@Controller
public class MemController {

	@Autowired
	private MemDAO dao;
	
	public MemController() {
		
	}
	
	public void setDao(MemDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/list.htm")
	public ModelAndView process(ModelAndView mav) {
	mav.addObject("list", dao.list());
	mav.setViewName("part01/list");
		return mav;
	}
	
	@RequestMapping(value = "/insert.htm", method = RequestMethod.GET)
	public String insertForm() {
		return "part01/insert";
	}
	
	
	@RequestMapping(value = "/insert.htm", method = RequestMethod.POST)
	public String insertData(MemDTO dto) {
		dao.insertMethod(dto);
		
		return "redirect:/list.htm";
	}
	
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public ModelAndView updateForm(int num, ModelAndView mav) {
		mav.addObject("dto",dao.one(num));
		mav.setViewName("part01/update");
		return mav;
	}
	
	@RequestMapping(value = "/update.htm", method = RequestMethod.POST)
	public String updateData(MemDTO dto) {
		dao.updateMethod(dto);
		return "redirect:/list.htm";
	}
	
	@RequestMapping(value = "/delete.htm")
	public String deleteData(int num) {
		dao.deleteMethod(num);
		return "redirect:/list.htm";
	}
}
