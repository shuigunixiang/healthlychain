package cc.manguo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;

public class BlockChain {

	private ArrayList<Block> chain;

	public BlockChain() {
		chain = new ArrayList<Block>();
		System.out.println("区块链生成...");
		chain.add(creationBlock());
		System.out.println("创世区块创建...");
	}

	/**
	 * 创世节点
	 * 
	 * @return
	 */
	private Block creationBlock() {
		int index = 0;
		String prevHash = "";
		long timestamp = System.currentTimeMillis();
		String data = "i am the Creation Block";
		String hash = genHash(index, prevHash, timestamp, data);
		return new Block(index, prevHash, timestamp, data, hash);
	}

	/**
	 * 最后节点
	 * 
	 * @return
	 */
	private Block lasteBlock() {
		return chain.get(chain.size() - 1);
	}

	/**
	 * 计算节点hash值
	 * 
	 * @param index
	 * @param prevHash
	 * @param data
	 * @return
	 */
	private String genHash(int index, String prevHash, long timestamp, String data) {
		MessageDigest md = null;
		try {
			String str = index + prevHash + timestamp + data;
			md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 创建一个节点
	 * 
	 * @param data
	 * @return
	 */
	public Block genBlock(String data) {
		Block pre_block = lasteBlock();
		int index = pre_block.getIndex() + 1;
		String prevHash = pre_block.getHash();
		long timestamp = System.currentTimeMillis();
		String hash = genHash(index, prevHash, timestamp, data);
		System.out.println(MessageFormat.format("区块{0}创建...", index));
		return new Block(index, prevHash, timestamp, data, hash);
	}

	/**
	 * 添加节点
	 *
	 * @param block
	 */
	public void add2Chain(Block block) {
		System.out.println(MessageFormat.format("区块{0}写入中...", block.getIndex()));
		Block pre_block = lasteBlock();
		if (validateBlock(block, pre_block)) {
			chain.add(block);
			System.out.println(MessageFormat.format("区块{0}写入完成...", block.getIndex()));
		} else {
			System.out.println(MessageFormat.format("区块{0}禁止写入...", block.getIndex()));
		}
	}

	/**
	 * 节点信任验证
	 * 
	 * @param block
	 * @param pre_block
	 * @return
	 */
	private boolean validateBlock(Block block, Block pre_block) {
		String hash = genHash(block.getIndex(), block.getPrevHash(), block.getTimestamp(), block.getData());
		if (!hash.equals(block.getHash())) {
			System.out.println(MessageFormat.format("区块{0}数据被篡改...", block.getIndex()));
			return false;
		}
		if (!block.getPrevHash().equals(pre_block.getHash())) {
			System.out.println(MessageFormat.format("区块{0}数据被篡改...", block.getIndex()));
			return false;
		}
		if (block.getIndex() != pre_block.getIndex() + 1) {
			System.out.println(MessageFormat.format("区块{0}数据被篡改...", block.getIndex()));
			return false;
		}
		return true;
	}

	/**
	 * 获取区块链
	 * 
	 * @return
	 */
	public ArrayList<Block> getChain() {
		return chain;
	}

}
