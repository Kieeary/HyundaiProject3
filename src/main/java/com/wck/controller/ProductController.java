package com.wck.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wck.domain.BrandCategoryVO;
import com.wck.domain.CategoryVO;
import com.wck.domain.FirstCategoryVO;
import com.wck.domain.ProductColorChipVO;
import com.wck.domain.ProductInfoVO;
import com.wck.domain.ProductStockVO;
import com.wck.domain.ProductVO;
import com.wck.domain.SecondCategoryVO;
import com.wck.domain.ThirdCategoryVO;
import com.wck.domain.WithProductVO;
import com.wck.security.domain.Account;
import com.wck.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller	
@RequestMapping("/wck/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	/*
	 * author : 정기범
	 * purpose : 상품 목록을 보여주는 페이지를 렌더링. 이때 쿼리스트링으로 들어온 카테고리값에 따라 상품이 조회됨
	 * 
	 */
	// 상품 조회
	@GetMapping("/list")
	@Transactional
	public String getBrandProducts(Model model,
									@RequestParam(value="brand", required=false) String brand,
									@RequestParam(value="gender", required=false) String gender,
									@RequestParam(value="secCat", required=false) String secCat,
									@RequestParam(value="thrCat", required=false) String thrCat) {	
		
		CategoryVO category = new CategoryVO();

		if(brand != null)	category.setBrand(brand);
		if(gender != null)	{
			category.setGender(gender);
			category.setGendername(productService.gender(gender));
			List<SecondCategoryVO> secondCategory = productService.getSecondCategory(gender, brand);
			model.addAttribute("detailcategory", secondCategory);
		}
		if(secCat != null)	{
			category.setSecCat(secCat);
			category.setSecCatname(productService.secondCategory(secCat));
			List<ThirdCategoryVO> thirdCategory = productService.getThirdCategory(secCat,brand);
			model.addAttribute("detailcategory", thirdCategory);
		}
		
		if(thrCat != null)	category.setThrCat(thrCat);
						
		int cnt = productService.getProductsCount(category.getBrand(), category.getGender(), category.getSecCat(), category.getThrCat());
				
		model.addAttribute("category", category);
		model.addAttribute("count", cnt);
		
		return "wck/product/list";
	}
	
	@ResponseBody
	@GetMapping("/list/p")
	public Object list(@RequestParam(value="brand", required=false) String brand,
					   @RequestParam(value="gender", required=false) String gender,
					   @RequestParam(value="secCat", required=false) String secCat,
					   @RequestParam(value="thrCat", required=false) String thrCat,
					   @RequestParam(value="start") int start, @RequestParam(value="last") int last) {
		
		String br = brand;
		String gd = gender;
		String sC = secCat;
		String tC = thrCat;
		
		log.info(gd);
		log.info(br);
		log.info(sC);
		log.info(tC);
		log.info(start);
		log.info(last);
		
		List<ProductVO> list = productService.productList(br, gd, sC, tC, start, last);
		
		for(ProductVO a : list) {
			log.info(a.getPid());
		}
		
		return list;
		
	}
	
	@GetMapping("/p")
	public String getProductInfo(Model model, 
			@AuthenticationPrincipal Account account,
			@RequestParam String pid, @RequestParam String pcid) {
		String pi = pid;
		String pci = pcid;
		log.info(pid);
		log.info(pci);
		
		ProductInfoVO productInfo = productService.getProductInfo(pcid, pid);
		
		List<String> sizeset = productService.getSizeSet(pcid);
		
		List<ProductColorChipVO> colorChip = productService.getColorChip(pid);
		List<WithProductVO> withProduct = productService.getWithProducts(pcid);
		boolean isLike = false;
		if(account != null) isLike = productService.isLikeProduct(account.getId(), pid);
		
		log.info("size" + productInfo.getSizeNstock().size());
		
		for(int i =0; i<productInfo.getSizeNstock().size(); i++) {
			productInfo.getSizeNstock().get(i).setPSize(sizeset.get(i));
		}
		for(ProductStockVO a: productInfo.getSizeNstock()) {
			for(String str : sizeset) {
				a.setPsId(str);
			}
		}
		model.addAttribute("sizeset", sizeset);
		model.addAttribute("productInfo", productInfo);
		model.addAttribute("productColor", colorChip);
		model.addAttribute("withProduct", withProduct);
		model.addAttribute("isLike", isLike);
		
	
		for(ProductColorChipVO a : colorChip) {
			log.info("=======" + a.getPcid());
			log.info("=======" + a.getPcchipimg());
		}
		return "wck/product/product_detail";
	}
	
	@GetMapping("/p/chgColor")
	@ResponseBody
	public Map<String, Object> chgProductColor(@RequestParam String pid, @RequestParam String pcid) {
		
		log.info(pid);
		log.info(pcid);
		
		Map<String, Object> info = new HashMap<>();
		
		ProductInfoVO productInfo = productService.getProductInfo(pcid, pid);
		List<WithProductVO> withProductInfo = productService.getWithProducts(pcid);
		info.put("productInfo", productInfo);
		info.put("withProductInfo", withProductInfo);
		
		log.info(productInfo.getPcid());
		
		return info;
	}
	
	@ResponseBody
	@GetMapping("/category")
	public Object getCategory() {
		
		List<FirstCategoryVO> category = productService.getCategoryName();
		
		return category;
	}
	
	@ResponseBody
	@GetMapping("/brand")
	public Object getBrandName() {
		
		List<BrandCategoryVO> brand = productService.getBrandCategory();
		return brand;
	}
	
	@GetMapping("/brandinfo")
	public String getBrandPage(Model model,
			@RequestParam(value="brand", required=false) String brand,
			@RequestParam(value="gender", required=false) String gender,
			@RequestParam(value="secCat", required=false) String secCat,
			@RequestParam(value="thrCat", required=false) String thrCat) {
		
		CategoryVO category = new CategoryVO();

		if(brand != null)	{
			category.setBrand(brand);
			int pi = Integer.parseInt(brand);
			String bname = productService.brand(pi);
			String brImg = productService.getBrandImg(brand);
			List<FirstCategoryVO> firstCategory = productService.getFirstCategory(brand);
			model.addAttribute("bname", bname);
			model.addAttribute("detailcategory", firstCategory);
			model.addAttribute("brand", brImg);
			
			if(gender != null)	{
				category.setGender(gender);
				List<SecondCategoryVO> secondCategory = productService.getSecondCategory(gender, brand);
				model.addAttribute("detailcategory", secondCategory);
				if(secCat != null)	{
					category.setSecCat(secCat);
					List<ThirdCategoryVO> thirdCategory = productService.getThirdCategory(secCat,brand);
					model.addAttribute("detailcategory", thirdCategory);
				}
			}
		}


		
		if(thrCat != null)	category.setThrCat(thrCat);
		
		
		int cnt = productService.getProductsCount(category.getBrand(), category.getGender(), category.getSecCat(), category.getThrCat());
		
		model.addAttribute("category", category);
		model.addAttribute("count", cnt);
		
		
		return "wck/product/brand";
	}
	
	@GetMapping("/brandinfo/p")
	@ResponseBody
	public Object getBrandProduct(@RequestParam(value="brand", required=false) String brand,
			   @RequestParam(value="gender", required=false) String gender,
			   @RequestParam(value="secCat", required=false) String secCat,
			   @RequestParam(value="thrCat", required=false) String thrCat,
			   @RequestParam(value="start") int start, @RequestParam(value="last") int last) {
		
		String br = brand;
		String gd = gender;
		String sC = secCat;
		String tC = thrCat;
		
		List<ProductVO> list = productService.productList(br, gd, sC, tC, start, last);
		
		for(ProductVO a : list) {
			log.info(a.getPid());
		}
		
		return list;
		
	}
	
}
