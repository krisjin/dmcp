package com.hx.dmcp.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hx.dmcp.entity.VisaItem;
import com.hx.dmcp.entity.vo.PageVo;
import com.hx.dmcp.service.VisaItemService;
import com.hx.dmcp.util.HttpUtils;

@Controller
@RequestMapping("/admin/vc")
public class VisaItemAction {

	@Autowired
	private VisaItemService visaItemServcie;

	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String addVisaItem(ModelMap model) {

		PageVo<VisaItem> visas = visaItemServcie.getVisaItemWithPage(1);
		model.put("visas", visas);
		return "system/vc/add";
	}

	@RequestMapping(value = "/save.htm", method = RequestMethod.POST)
	public String saveVisaItem(@RequestParam(value = "name") String name, @RequestParam(value = "sort") int sort,
			@RequestParam(value = "display") int display, ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		VisaItem item = new VisaItem();
		item.setName(name);
		item.setSort(sort);
		item.setDisplay(display);
		visaItemServcie.addVisaItem(item);

		String path = HttpUtils.getBasePath(request) + "/admin/vc/list.htm";
		response.sendRedirect(path);
		return null;
	}

	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	public String getVisaItem(ModelMap model) {
		PageVo<VisaItem> page = visaItemServcie.getVisaItemWithPage(1);
		model.put("pageVo", page);
		model.put("flag", "query");
		return "system/vc/list";
	}

	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String update(@RequestParam(value = "id") long id, ModelMap model) {
		PageVo<VisaItem> page = visaItemServcie.getVisaItemWithPage(1);
		VisaItem vi = visaItemServcie.getVisaItemById(id);
		model.put("pageVo", page);
		model.put("flag", "update");
		model.put("vi", vi);
		return "system/vc/list";
	}

	@RequestMapping(value = "/updateSave.htm", method = RequestMethod.POST)
	public String updateSave(@RequestParam(value = "name") String name, @RequestParam(value = "id") long id,
			@RequestParam(value = "sort") int sort, @RequestParam(value = "display") int display, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		VisaItem vi = new VisaItem();
		vi.setId(id);
		vi.setName(name);
		vi.setSort(sort);
		vi.setDisplay(display);
		visaItemServcie.updateVisaItem(vi);

		String path = HttpUtils.getBasePath(request) + "/admin/vc/list.htm";
		response.sendRedirect(path);
		return null;
	}

	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		visaItemServcie.deleteVisaItem(id);
		String path = HttpUtils.getBasePath(request) + "/admin/vc/list.htm";
		response.sendRedirect(path);
		return null;
	}
}
