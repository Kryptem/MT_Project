package mortuusterra.utils;

public class ElapsedTime {

	private long timeStart;
	private long timeStop;
	private long timeNow;
	private long elapsedTime;
	private long serverStartTime;
	
	public long getTimeStart() {
		return this.timeStart;
	}
	public void setTimeStart(long timeStart) {
		this.timeStart = System.nanoTime();
		timeStart = this.timeStart;
	}
	public long getTimeStop() {
		return this.timeStop;
	}
	public void setTimeStop(long timeStop) {
		this.timeStop = System.nanoTime();
		timeStop = this.timeStop;
	}
	public long getTimeNow() {
		return this.timeNow;
	}
	public void setTimeNow(long timeNow) {
		this.timeNow = System.nanoTime();
		timeNow = this.timeNow;
	}
	public long getElapsedTime() {
		return this.elapsedTime;
	}
	public void setElapsedtime(long elapsedTime) {
		setTimeNow(this.timeNow);
		this.elapsedTime = (this.timeStart - this.timeNow);
		elapsedTime = this.elapsedTime;
	}
	
	public void setupStartTime() {
		setTimeStart(this.timeStart);
	}
	public void setupStopTime() {
		setTimeStop(this.timeStop);
	}
	public void setupElapsedtime() {
		setElapsedtime(this.elapsedTime);
	}
	public long getServerStartTime() {
		return this.serverStartTime;
	}
	public void setServerStartTime(long serverStartTime) {
		this.serverStartTime = System.nanoTime();
		serverStartTime = this.serverStartTime;
	}
	public void setupServerStartTime() {
		setServerStartTime(this.serverStartTime);
	}
}
