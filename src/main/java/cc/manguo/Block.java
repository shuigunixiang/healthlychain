package cc.manguo;

public class Block {

	private int index;
	private String prevHash;
	private long timestamp;
	private String data;
	private String hash;

	public Block(int index, String prevHash, long timestamp, String data, String hash) {
		this.index = index;
		this.prevHash = prevHash;
		this.timestamp = timestamp;
		this.data = data;
		this.hash = hash;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPrevHash() {
		return prevHash;
	}

	public void setPrevHash(String prevHash) {
		this.prevHash = prevHash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
