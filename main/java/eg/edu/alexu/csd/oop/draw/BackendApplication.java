package eg.edu.alexu.csd.oop.draw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
@CrossOrigin
@SpringBootApplication
@RestController
public class BackendApplication {
	DrawEngine engine = DrawEngine.getInstance();

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@RequestMapping({"/draw"})
	public void drawShape(@RequestBody String shapeJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<?, ?> map = null;
	try {
		map = objectMapper.readValue(shapeJson,Map.class);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}

	engine.addShape(map);
	}
	@RequestMapping({"/update"})
	public void updateShape(@RequestBody String shapeJson){
		ObjectMapper objectMapper=new ObjectMapper();
		Map<?,?> map=null;
		try{
			map=objectMapper.readValue(shapeJson,Map.class);
		}catch(JsonProcessingException e){
			e.printStackTrace();
		}
		engine.updateShape(map);
	}
	@RequestMapping({"/remove"})
		public void removeShape(@RequestBody int id){
			engine.removeShape(id);
		}

	}
