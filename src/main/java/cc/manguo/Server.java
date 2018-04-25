package cc.manguo;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import com.google.gson.Gson;

public class Server {

	public static void main(String[] args) throws InterruptedException {
		port(8001);
		BlockChain chain = new BlockChain();
		Gson gson = new Gson();

		// 欢迎页
		get("/", (req, res) -> "welcome healthly chain!");

		// 查询链
		get("/query", (req, res) -> {
			return gson.toJson(chain.getChain());
		});

		// 产生节点
		post("/gen", (req, res) -> {
			return gson.toJson(chain.genBlock(req.queryParams("data")));
		});

		// 添加节点
		post("/add", (req, res) -> {
			Block block = new Block(Integer.parseInt(req.queryParams("index")), req.queryParams("prevHash"),
					Long.parseLong(req.queryParams("timestamp")), req.queryParams("data"), req.queryParams("hash"));
			chain.add2Chain(block);
			return "done";
		});

	}

}
