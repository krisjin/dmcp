package com.hx.dmcp.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hx.dmcp.entity.Visa;
import com.hx.dmcp.entity.VisaItem;
import com.hx.dmcp.entity.vo.PageVo;
import com.hx.dmcp.service.VisaItemService;
import com.hx.dmcp.service.VisaService;
import com.hx.dmcp.util.HttpUtils;

/**
 * @author krisjin
 */
@Controller
@RequestMapping("/admin/visa")
public class VisaAction {

	@Autowired
	private VisaService visaService;

	@Autowired
	private VisaItemService visaItemService;

	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String addVisa(ModelMap model) {

		PageVo<VisaItem> visaItem = visaItemService.getVisaItemWithPage(1);
		model.put("visaItem", visaItem);
		model.put("flag", "add");
		return "system/visa/add";
	}

	@RequestMapping(value = "/save.htm", method = RequestMethod.POST)
	public String saveVisa(@RequestParam(value = "title") String title, ModelMap model, @RequestParam(value = "visaType") int visaType,
			@RequestParam(value = "handleDate") String handleDate, @RequestParam(value = "cost") String cost,
			@RequestParam(value = "costDescription") String costDescription, @RequestParam(value = "acceptPeople") String acceptPeople,
			@RequestParam(value = "content") String content, @RequestParam(value = "category") long category,
			@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Visa visa = new Visa();
		visa.setTitle(title);
		visa.setAcceptPeople(acceptPeople);
		visa.setCategory(category);
		visa.setContent(content);
		visa.setCost(cost);
		visa.setCostDescription(costDescription);
		visa.setCreateDate(new Date());
		visa.setHandleDate(handleDate);
		visa.setVisaType(visaType);

		String visaFolder = request.getRealPath("/") + "/visa/";
		File tmpFile = new File(visaFolder);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		File destFile = new File(tmpFile, file.getOriginalFilename());

		FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);

		visa.setAvatarUrl("visa/" + file.getOriginalFilename());
		visaService.addVisa(visa);

		String path = HttpUtils.getBasePath(request) + "/admin/visa/list.htm";
		response.sendRedirect(path);
		return null;
	}

	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	public String getVisa(ModelMap model, @RequestParam(value = "p", defaultValue = "1") int pageNum) {
		PageVo<Visa> page = visaService.getVisaWithPage(pageNum);
		List<Visa> visas = page.getList();
		for (Visa visa : visas) {
			VisaItem item = visaItemService.getVisaItemById(visa.getCategory());
			visa.setCategoryName(item.getName());
		}
		page.setList(visas);

		model.put("pageVo", page);
		model.put("flag", "query");
		return "system/visa/list";
	}

	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String update(@RequestParam(value = "id") long id, ModelMap model) {
		PageVo<VisaItem> visaItem = visaItemService.getVisaItemWithPage(1);
		Visa visa = visaService.getVisaById(id);
		model.put("visaItem", visaItem);
		model.put("flag", "update");
		model.put("visa", visa);
		return "system/visa/add";
	}

	@RequestMapping(value = "/updateSave.htm", method = RequestMethod.POST)
	public String updateSave(@RequestParam(value = "title") String title, ModelMap model, @RequestParam(value = "visaType") int visaType,
			@RequestParam(value = "handleDate") String handleDate, @RequestParam(value = "cost") String cost,
			@RequestParam(value = "costDescription") String costDescription, @RequestParam(value = "acceptPeople") String acceptPeople,
			@RequestParam(value = "content") String content, @RequestParam(value = "category") long category,
			@RequestParam(value = "file") CommonsMultipartFile file, @RequestParam(value = "id") long id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Visa tmp = visaService.getVisaById(id);
		Visa visa = new Visa();
		visa.setTitle(title);
		visa.setAcceptPeople(acceptPeople);
		visa.setCategory(category);
		visa.setContent(content);
		visa.setCost(cost);
		visa.setCostDescription(costDescription);
		visa.setTitle(title);
		visa.setVisaType(visaType);
		visa.setHandleDate(handleDate);
		visa.setId(id);

		if (file.getOriginalFilename().equals("")) {
			visa.setAvatarUrl(tmp.getAvatarUrl());
		} else {
			String visaFolder = request.getRealPath("/") + "/visa/";
			File destFile = new File(new File(visaFolder), file.getOriginalFilename());

			FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);

			visa.setAvatarUrl(destFile.getAbsolutePath());
		}

		visaService.updateVisa(visa);

		String path = HttpUtils.getBasePath(request) + "/admin/visa/list.htm";
		response.sendRedirect(path);
		return null;
	}

	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		visaService.deleteVisa(id);
		String path = HttpUtils.getBasePath(request) + "/admin/vc/list.htm";
		response.sendRedirect(path);
		return null;
	}

}
