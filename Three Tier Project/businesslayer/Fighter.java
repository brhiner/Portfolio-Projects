package businesslayer;

public class Fighter {

	//**********INSTANCE VARIABLES**********//
	private String name, nickname;
	private int power = 3, health = 3, mobility = 3, techniques = 3, range = 3;
	
	//**********CONSTRUCTORS**********//
	public Fighter(String name, String nickname, int power, int health,
			int mobility, int techniques, int range) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.power = power;
		this.health = health;
		this.mobility = mobility;
		this.techniques = techniques;
		this.range = range;
	} //end constructor
	
	//**********GETTERS/SETTERS**********//
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMobility() {
		return mobility;
	}
	public void setMobility(int mobility) {
		this.mobility = mobility;
	}
	public int getTechniques() {
		return techniques;
	}
	public void setTechniques(int techniques) {
		this.techniques = techniques;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	
	//**********INSTANCE METHODS**********//
	public String toString(){
		return name;
	}
}
