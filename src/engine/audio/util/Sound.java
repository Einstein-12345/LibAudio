package engine.audio.util;

import java.util.UUID;

import javax.sound.sampled.LineListener;

import engine.audio.loader.SoundLoader;
import engine.audio.loader.SoundLoader.SoundInfo;

public class Sound {
	private SoundInfo info;
	private boolean playing;

	public Sound(SoundLoader.SoundInfo info) {
		this.info = info;
	}

	public void play() throws Exception {
		info.getAudio().open();
		info.getAudio().start();
		playing = true;
	}

	public void stop() {
		info.getAudio().stop();
		info.getAudio().close();
		playing = false;
	}

	public void setPosition(long ms) {
		info.getAudio().setMicrosecondPosition(ms);
	}

	public void addLineListener(LineListener l) {
		info.getAudio().addLineListener(l);
	}

	public UUID getUUID() {
		return info.getUUID();
	}

	public boolean isPlaying() {
		return playing;
	}
}
