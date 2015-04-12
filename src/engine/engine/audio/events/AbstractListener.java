package engine.audio.events;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import engine.audio.util.Sound;

public abstract class AbstractListener implements LineListener {
	private Sound s;

	@Override
	public void update(LineEvent event) {
		update(event, s);
	}

	public void listenTo(Sound s) {
		this.s = s;
	}

	public abstract void update(LineEvent e, Sound s);

}
