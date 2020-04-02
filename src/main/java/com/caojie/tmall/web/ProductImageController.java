package com.caojie.tmall.web;

import com.caojie.tmall.pojo.Product;
import com.caojie.tmall.pojo.ProductImage;
import com.caojie.tmall.service.ProductImageService;
import com.caojie.tmall.service.ProductService;
import com.caojie.tmall.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.caojie.tmall.service.ProductImageService.type_detail;
import static com.caojie.tmall.service.ProductImageService.type_single;

@RestController
public class ProductImageController {
    @Autowired ProductImageService productImageService;
    @Autowired
    ProductService productService;

    @GetMapping("/products/{pid}/productImages")
    public List<ProductImage> list(@RequestParam("type") String type, @PathVariable("pid") int pid)throws Exception{
        if(type_single.equals(type)) {
            return productImageService.listSingleProductImages(productService.get(pid));
        } else if(type_detail.equals((type))){
            return productImageService.listDetailProductImages(productService.get(pid));
        }else {
            return new ArrayList<>();
        }
    }

    //上传产品图片
    @PostMapping("/productImages")
    public Object add(HttpServletRequest request, @RequestParam("pid") int pid, @RequestParam("type") String type, MultipartFile image)throws Exception{
        ProductImage bean = new ProductImage();
        Product product = productService.get(pid);
        bean.setProduct(product);
        bean.setType(type);
        productImageService.add(bean);
        //图片处理
        String folder = "img/";
        if(type_single.equals(type)){
            folder+="productSingle";
        }else{
            folder+="productDetail";
        }
        //创建路径对象
        File imageFolder = new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder,bean.getId()+"jpg");
        String fileName = file.getName();
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
            //file.mkdirs();
        }try{
            //不太理解
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img,"jpg",file);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(type_single.equals(type)){
            String imageFolder_small = request.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle= request.getServletContext().getRealPath("img/productSingle_middle");
            File f_small = new File(imageFolder_small,fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.getParentFile().mkdirs();
            f_middle.getParentFile().mkdirs();
            ImageUtil.resizeImage(file, 56, 56, f_small);
            ImageUtil.resizeImage(file, 217, 190, f_middle);
        }
        return  bean;
    }
}
