package engine.audio.util;

import javax.sound.sampled.LineListener;

import engine.audio.loader.SoundLoader;
import engine.audio.loader.SoundLoader.SoundInfo;

public class Sound {
	private SoundInfo info;

	public Sound(SoundLoader.SoundInfo info) {
		this.info = info;
	}

	public void play() throws Exception {
		info.getAudio().open();
		info.getAudio().start();
	}

	public void stop() {
		info.getAudio().stop();
		info.getAudio().close();
	}

	public void setPosition(long ms) {
		info.getAudio().setMicrosecondPosition(ms);
	}

	public void addLineListener(LineListener l) {
		info.getAudio().addLineListener(l);
	}
}
