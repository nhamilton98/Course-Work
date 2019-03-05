package acg.architecture.view.glyph.loader;

import java.io.IOException;

public class GlyphLoaderTester {

	public static void main(String[] args) throws InvalidLayoutException, IOException {
			GlyphLoader load = new GlyphLoader("test.txt");
			load.load();
	}

}
