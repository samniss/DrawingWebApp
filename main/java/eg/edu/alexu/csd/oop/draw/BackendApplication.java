package eg.edu.alexu.csd.oop.draw;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@SpringBootApplication
@RestController
public class BackendApplication {
	DrawEngine engine = DrawEngine.getInstance();

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@RequestMapping({ "/draw" })
	public void drawShape(@RequestBody String shapeJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<?, ?> map = null;
		try {
			map = objectMapper.readValue(shapeJson, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		engine.addShape(map);
	}

	@RequestMapping({ "/update" })
	public void updateShape(@RequestBody String shapeJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<?, ?> map = null;
		try {
			map = objectMapper.readValue(shapeJson, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		engine.updateShape(map);
	}

	@RequestMapping({ "/remove" })
	public IShape removeShape(@RequestParam int id) {
		return engine.removeShape(id);
	}

	@RequestMapping({ "/undo" })
	public IShape undo() {
		return engine.undo();
	}

	@RequestMapping({ "/redo" })
	public IShape redo() {
		return engine.redo();
	}

	@RequestMapping({ "/copy" })
	public IShape copy(int id) {
		return engine.copyShape(id);
	}

	@RequestMapping({ "/save" })
	public void save(@RequestParam String saveFile,@RequestParam String ext) {
		String fileName = saveFile + "." + ext;
		System.out.println(fileName);
		if(fileName.contains(".json")){
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			try {
				objectMapper.writeValue(new File(fileName), engine);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			try{
			FileOutputStream fos = new FileOutputStream(fileName);
    		XMLEncoder encoder = new XMLEncoder(fos,"ISO-8859-1", false, 0);
    		encoder.setExceptionListener(new ExceptionListener() {
            	public void exceptionThrown(Exception e) {
                	System.out.println("Exception! :"+e.toString());
            	}
   			 });
    		encoder.writeObject(engine);
    		encoder.close();
			fos.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}

	@RequestMapping({ "/load" })
	public ArrayList<IShape> load(@RequestParam String uploadedFile) {
		engine.redo.clear();
		engine.undo.clear();
		engine.getShapes().clear();
		if(uploadedFile.contains(".json")){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		try {
			this.engine = objectMapper.readValue(new File(uploadedFile), DrawEngine.class);
			objectMapper.writeValue(new File("newObject6.json"), engine);
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(engine);
		
		}else{
			try{
			FileInputStream fis = new FileInputStream(uploadedFile);
			XMLDecoder decoder = new XMLDecoder(fis);
			engine = (DrawEngine) decoder.readObject();
			decoder.close();
			fis.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		engine.redo.clear();
		engine.undo.clear();
		return this.engine.getShapes();
		
        
	}
}

