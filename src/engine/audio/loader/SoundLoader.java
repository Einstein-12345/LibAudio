package engine.audio.loader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

import engine.audio.util.Sound;

public class SoundLoader {
	public static Sound loadSound(String file) {
		final File f = new File(file);
		if (f.exists() && (!f.isDirectory())) {
			try {
				return _loadSound(f.toURI().toURL());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Sound loadSound(URL url) {
		return _loadSound(url);
	}

	private static Sound _loadSound(URL url) {
		try {
			return new Sound(new SoundInfo(url,
					AudioSystem.getAudioInputStream(url)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static class SoundInfo {
		private URL file;
		private AudioInputStream in;
		private AudioFormat format;
		private Clip clip;

		public SoundInfo(URL file, AudioInputStream in)
				throws LineUnavailableException {
			this.in = in;
			format = in.getFormat();
			clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class,
					format));
		}

		public Clip getAudio() {
			return clip;
		}

		public AudioFormat getFormat() {
			return format;
		}

		public String getFile() {
			return file.toString();
		}
	}
}