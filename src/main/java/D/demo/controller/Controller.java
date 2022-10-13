package D.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import org.hibernate.Criteria;
// import org.hibernate.Session;
// import org.hibernate.SessionFactory;
// import org.hibernate.cfg.Configuration;
// import org.hibernate.criterion.Projections;
// import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import D.demo.service.PasseioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import D.demo.model.Passeio;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/passeio")
@Api(value = "API REST Produtos")
@CrossOrigin(origins = "*")
public class Controller {

    // create controller 6:32

    @Value("${uploadDir}")
    private String uploadFolder;

    @Autowired
    PasseioService passeioService;

    // @GetMapping(value = "/" )
    // public String homePage() {
    // return "index";
    // }

    @GetMapping(value = "/teste")
    @ApiOperation(value = "Retorna uma lista de produto")
    public void listaProdutos() {
        System.out.println("Teste aqui!");
    }

    @PostMapping("/teste")
    String testing(@RequestBody String data) {

        System.out.println(data);
        return "redirect:/";
    }

    // @GetMapping("/produto/{id}")
    // @ApiOperation(value = "Retorna um produto")
    // public Passeio listaProduto(@PathVariable(value = "id") long id) {
    // return passeioService.findById(id);
    // }

    // @PostMapping("/produto")
    // @ApiOperation(value = "Cadastra um produto")
    // public Passeio salvaProduto(@RequestBody Passeio produto) {
    // return passeioService.save(produto);
    // }

    // @DeleteMapping("/produto")
    // @ApiOperation(value = "Deleta um produto")
    // public void deletaProduto(@RequestBody Passeio produto) {
    // passeioService.delete(produto);
    // }

    // @PutMapping("/produto")
    // @ApiOperation(value = "Atualiza um produto")
    // public void atualizaProduto(@RequestBody Passeio passeio) {
    // passeioService.savePasseio(passeio);
    // }

    // A multipart request is a REST request containing several packed REST requests inside its entity.

    @PostMapping(value = "/save")
    public @ResponseBody ResponseEntity<?> createEmpoyee(@RequestParam("name") String name,
        @RequestParam("price") double price, @RequestParam("quantidade") int quantidade, Model model,
        HttpServletRequest request, final @RequestParam("image") MultipartFile file) {

        // System.out.println("Price: " + price);
        System.out.println("File: \n" + file);

        try {
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            // log.info("uploadDirectory:: " + uploadDirectory);
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            // log.info("FileName: " + file.getOriginalFilename());
            if (fileName == null || fileName.contains("..")) {
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence\" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName,
                        HttpStatus.BAD_REQUEST);
            }
            String[] names = name.split(",");
            // String[] descriptions = description.split(",");
            // Date createDate = new Date();

            // log.info("Name: " + names[0] + " " + filePath);
            // log.info("description: " + descriptions[0]);
            // log.info("price: " + price);
            try {
                File dir = new File(uploadDirectory);
                if (!dir.exists()) {
                    // log.info("Folder Created");
                    dir.mkdirs();
                }
                // Save the file locally
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                // log.info("in catch");
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            Passeio passeio = new Passeio();
            passeio.setNome(names[0]);
            passeio.setImage(imageData);
            passeio.setValor(price);
            passeio.setQuantidade(quantidade);
            passeioService.savePasseio(passeio);
            // log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
            return new ResponseEntity<>("Product Saved With File - " + fileName,
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // log.info("Exception: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Passeio> passeio)
			throws ServletException, IOException {
		// log.info("Id :: " + id);
        System.out.println("Get file");
		passeio = passeioService.getPasseioById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(passeio.get().getImage());
		response.getOutputStream().close();
	}

    
    @GetMapping("/getall")
	List<Passeio> show(Model map) {
		List<Passeio> passeios = passeioService.getAllPasseios();
	
        // map.addAttribute("passeios", passeios);
        
        /* IMPLEMENT LATER !!!!! */
        // SessionFactory factory = new Configuration().configure().buildSessionFactory();
        // Session session = factory.openSession();
        // Criteria cr = session.createCriteria(Passeio.class)
        //     .setProjection(Projections.projectionList()
        //     .add(Projections.property("id"), "id")
        //     .add(Projections.property("nome"), "nome")
        //     .add(Projections.property("quantidade"), "quantidade")
        //     .add(Projections.property("valor"), "valor"));

		return passeios;
    }

    /*
     * return multipart request
     * https://stackoverflow.com/questions/46286939/spring-rest-return-multipart-form-data-with-application-json-content-type
     */
}
