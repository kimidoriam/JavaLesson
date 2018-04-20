public class Rectangle {
	int width;   // 幅
	int height;  // 高さ
	// コンストラクタ
	public Rectangle (int width, int height) {
		this.width = width;
		this.height = height;
	}
	// 文字列表現
	@Override
	public String toString() {
		return "[ " + width + ", " + height + " ]";
	}
}
