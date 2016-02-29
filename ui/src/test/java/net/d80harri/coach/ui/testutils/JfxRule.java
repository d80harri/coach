package net.d80harri.coach.ui.testutils;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class JfxRule implements TestRule {
	private static boolean started = false;

	@Override
	public Statement apply(final Statement arg0, Description arg1) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				if (!started) {
					JavaFXInitializer.initialize();
				}
				started = true;
				arg0.evaluate();
			}
		};
	}

	public static class JavaFXInitializer extends Application {

		private static Object barrier = new Object();

		@Override
		public void start(Stage primaryStage) throws Exception {
			synchronized (barrier) {
				barrier.notify();
			}
		}

		public static void initialize() throws InterruptedException {
			Thread t = new Thread("JavaFX Init Thread") {
				public void run() {
					Application.launch(JavaFXInitializer.class, new String[0]);
				}
			};
			t.setDaemon(true);
			t.start();
			synchronized (barrier) {
				barrier.wait();
			}
		}
	}

}
