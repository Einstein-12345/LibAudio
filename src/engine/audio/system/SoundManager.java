package engine.audio.system;

import java.awt.GraphicsEnvironment;
import java.util.Hashtable;
import java.util.UUID;

import javax.swing.JOptionPane;

import engine.audio.util.Sound;

public final class SoundManager {
	private static Hashtable<String, Sound> byname = new Hashtable<String, Sound>();
	private static Hashtable<UUID, Sound> byuid = new Hashtable<UUID, Sound>();
	private static boolean playing;
	private static UUID sel;

	public static void addSound(Sound s) {
		byuid.put(s.getUUID(), s);
	}

	public static void addSound(Sound s, String name) {
		byname.put(name, s);
		byuid.put(s.getUUID(), s);
	}

	public static boolean isPlaying(UUID uid) {
		if (byuid.containsKey(uid)) {
			return byuid.get(uid).isPlaying();
		}
		return false;
	}

	public static boolean isPlaying(String name) {
		if (byname.containsKey(name)) {
			return byname.get(name).isPlaying();
		}
		return false;
	}

	public static boolean isInUse() {
		return playing;
	}

	public static void play(String name) {
		stop();
		if (byname.containsKey(name)) {
			try {
				byname.get(name).play();
				sel = byname.get(name).getUUID();
				playing = true;
			} catch (Exception e) {
				e.printStackTrace();
				if (!GraphicsEnvironment.isHeadless()) {
					JOptionPane.showMessageDialog(null,
							"Failed to play audio clip: " + name);
				}
				playing = false;
			}
		}
	}

	public static void stop() {
		if (playing) {
			byuid.get(sel).stop();
			sel = null;
			playing = false;
		}
	}

	public static void play(UUID uid) {
		stop();
		if (byuid.containsKey(uid)) {
			try {
				byuid.get(uid).play();
				sel = uid;
				playing = true;
			} catch (Exception e) {
				e.printStackTrace();
				if (!GraphicsEnvironment.isHeadless()) {
					JOptionPane.showMessageDialog(null,
							"Failed to play audio clip: " + uid.toString());
				}
				playing = false;
			}
		}
	}

}
