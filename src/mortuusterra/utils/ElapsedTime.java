package mortuusterra.utils;

public class ElapsedTime {

	private long timeStart;
	private long timeStop;
	private long timeNow;
	private long elapsedTime;
	
	public long getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(long timeStart) {
		this.timeStart = System.nanoTime();
	}
	public long getTimeStop() {
		return timeStop;
	}
	public void setTimeStop(long timeStop) {
		this.timeStop = System.nanoTime();
	}
	public long getTimeNow() {
		return timeNow;
	}
	public void setTimeNow(long timeNow) {
		this.timeNow = System.nanoTime();
	}
	public long getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedtime(long elapsedTime) {
		setTimeNow(timeNow);
		this.elapsedTime = (timeStart - timeNow);
	}
	
	public void setupStartTime() {
		setTimeStart(timeStart);
	}
	public void setupStopTime() {
		setTimeStop(timeStop);
	}
	public void setupElapsedtime() {
		setElapsedtime(elapsedTime);
	}
}
