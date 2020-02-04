package com.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.AddressDaoImpl;
import com.app.dao.CategoryDaoImpl;
import com.app.dao.ILoginDao;
import com.app.dao.IProductDao;
import com.app.dao.OrderDaoImpl;
import com.app.dao.SubCategoryDaoImpl;
import com.app.pojos.Address;
import com.app.pojos.Category;
import com.app.pojos.Order;
import com.app.pojos.Product;
import com.app.pojos.SubCategory;
import com.app.pojos.TransactionVO;
import com.app.pojos.User;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductController extends BaseController {
	private static String UPLOAD_DIR = "/home/sunbeam/Desktop/DacProject/frontend/pract1/upload/";
	@Autowired
	IProductDao productDao;
	

	@Autowired
	CategoryDaoImpl catDao;
	
	@Autowired
	ILoginDao loginDao;

	@Autowired
	SubCategoryDaoImpl subCatDao;
	
	@Autowired
	AddressDaoImpl addDao;
	
	@Autowired
	OrderDaoImpl oderDao;

	@GetMapping("/products")
	public List<Product> getAllProducts(HttpSession httpSession) {

		return productDao.getAllProducts();
	}

	@GetMapping("/products/categories")
	public List<Category> categories() {
		return catDao.getAllCategories();
	}

	@GetMapping("/products/subcategories")
	public List<SubCategory> subcategories() {
		return subCatDao.getAllSubCategories();
	}


	@GetMapping("/products/{id}")   
	public Product getProductById(@PathVariable(value = "id") Long productId) {
		System.out.println("product is::" + productId);

		return productDao.findById(productId);
	}

	@RequestMapping(path = "/order", method = RequestMethod.POST)
	public Order order(@RequestBody TransactionVO tr) throws IOException {
		Address address=addDao.persistAddress(tr.getAddress());
		User u =loginDao.getUserBySessionToken(tr.getSessionToken());
		Order o=oderDao.order(u, address,tr.getProduct());
		return o;
	}

	@RequestMapping(path = "/products", method = RequestMethod.POST, consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public Product createNote(@ModelAttribute Product product, HttpSession httpSession) throws IOException {
		System.out.println(product);
		User user = getLoggedInInUser(httpSession);

		Enumeration<String> attributes = httpSession.getAttributeNames();
		while (attributes.hasMoreElements()) {
			String attribute = (String) attributes.nextElement();
			System.out.println(attribute + " : " + httpSession.getAttribute(attribute));
		}

		System.out.println("user from response ::" + user);
		product.setUser(user);
		product.setCategory(catDao.getCategoryById(product.getCategoryId()));
		product.setSubCategory(subCatDao.getSubCategoryById(product.getSubCategoryId()));

		System.out.println("here product after enrichment ::" + product);
		if (product.getImage1File() != null) {
			product.setImage1("1_" + product.getImage1File().getOriginalFilename());
		}
		if (product.getImage2File() != null) {
			product.setImage2("2_" + product.getImage2File().getOriginalFilename());
		}

		if (product.getImage3File() != null) {
			product.setImage3("3_" + product.getImage3File().getOriginalFilename());
		}

		if (product.getImage4File() != null) {
			product.setImage4("4_" + product.getImage4File().getOriginalFilename());
		}
		Product p = productDao.save(product);
		System.out.println(p);
		saveFile(product.getImage1File(), p.getProductId(), 1);
		saveFile(product.getImage2File(), p.getProductId(), 2);
		saveFile(product.getImage3File(), p.getProductId(), 3);
		saveFile(product.getImage4File(), p.getProductId(), 4);

		return p;
	}

	private void saveFile(MultipartFile f, long productId, int number) throws IOException {

		// folder = /opt/upload

		OutputStream out = null;
		InputStream filecontent = null;
		try {
			File directory = new File(UPLOAD_DIR + File.separator + productId);
			if (!directory.exists()) {
				directory.mkdir();
			}
			out = new FileOutputStream(new File(
					UPLOAD_DIR + File.separator + productId + File.separator + number + "_" + f.getOriginalFilename()));
			filecontent = f.getInputStream();
			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
		}
	}

	@RequestMapping(value = "/image/{id}/{fileName}/", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable(value = "id") Long productId, @PathVariable(value = "fileName") String fileName,
			HttpServletResponse response) throws IOException {
		System.out.println("id ::" + productId);
		System.out.println("filename::" + fileName);
		File imgFile = new File(UPLOAD_DIR + productId + File.separator + fileName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(new FileInputStream(imgFile), response.getOutputStream());
	}

}
