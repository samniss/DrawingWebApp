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
	DrawEngine engine = DrawEngine.getInstance();
	engine.addShape(map);
	ArrayList<IShape> list = engine.getShapes();
	}
	}
