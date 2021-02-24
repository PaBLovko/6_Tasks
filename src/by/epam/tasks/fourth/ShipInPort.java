package by.epam.tasks.fourth;

/* Корабли заходят в порт для разгрузки/загрузки контейнеров. Число контейнеров,
 * находящихся в текущий момент в порту и на корабле, должно быть неотрицательным
 * и превышающим заданную грузоподъемность судна и вместимость порта. В порту
 * работает несколько причалов. У одного причала может стоять один корабль.
 * Корабль может загружаться у причала или разгружаться.
 * */

public class ShipInPort {
	private static Decision t1;
	private static Decision t2;
	private static Decision t4;
	private static Decision t5;
	private static Decision t3;

	public static Decision getMt1() {
		return t1;
	}

	public static void setMt1(Decision mt1) {
		ShipInPort.t1 = mt1;
	}

	public static Decision getMt2() {
		return t2;
	}

	public static void setMt2(Decision mt2) {
		ShipInPort.t2 = mt2;
	}

	public static Decision getMt4() {
		return t4;
	}

	public static void setMt4(Decision mt4) {
		ShipInPort.t4 = mt4;
	}

	public static Decision getMt5() {
		return t5;
	}

	public static void setMt5(Decision mt5) {
		ShipInPort.t5 = mt5;
	}

	public static Decision getMt3() {
		return t3;
	}

	public static void setMt3(Decision mt3) {
		ShipInPort.t3 = mt3;
	}

	public static void main(String args[]) {
		System.out.println("Start");
		setMt1(Decision.createAndStart("Ship 1"));
		setMt2(Decision.createAndStart("Ship 2"));
		setMt3(Decision.createAndStart("Ship 3"));
		setMt4(Decision.createAndStart("Ship 4"));
		setMt5(Decision.createAndStart("Ship 5"));

		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException exc) {
				System.out.println("Main thread interrupted.");
			}
		}
	}
}