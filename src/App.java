import java.util.Scanner;

public class App {
	
	//Decidi hacerlo estático para no utilizar el paradigma orientado a objetos
	
	//Tambien iba a utilizar Set para validar que el codigo token no se repita, pero
	//nuevamente decidí no utilizarlo para no involucrar objetos
	public static Integer generarToken(Integer[] registroToken, Integer intentosRestantes) {
		Integer generado = (int) 100000 + (int)(Math.random() * ((999999 - 100000) + 1));
		
		if(registroToken.length > 0) {
			for(int i=0; i<registroToken.length; i++) {
				if (generado.equals(registroToken[i])) {
					generado = generarToken(registroToken, intentosRestantes);
				}
			}
		}
		
		registroToken[3-registroToken.length] = generado;
		return generado;
	}
	
	public static Boolean validarDatos(String usuarioIngresado, String contrasenaIngresada, Integer tokenIngresado, Integer token) {
		//Credenciales para validar
		String usuario = "administrador";
		String contrasena = "administrador.1234";
		
		if(usuarioIngresado.equals(usuario)) {
			if(contrasenaIngresada.equals(contrasena)) {
				if(tokenIngresado.equals(token)) {
					return Boolean.TRUE;
				}else {
					return Boolean.FALSE;
				}
			}else {
				return Boolean.FALSE;
			}
		}else {
			return Boolean.FALSE;
		}
	}
	
	public static void inputDatos() {
		Integer intentosRestantes = 3;
		String usuarioIn = "", contrasenaIn= "", tokenIn = "", reintentar = "S";
		Integer token = 0;
		Integer registroToken[] = new Integer[3];
		Scanner console = new Scanner(System.in);
		Boolean loginExitoso = Boolean.FALSE;
		
		while (intentosRestantes > 0 && reintentar.equals("S")) {
			token = generarToken(registroToken, intentosRestantes);
			System.out.print("Clave token generada automáticamente: ");
			System.out.print(token.toString()+"\n");
			
			do{
				System.out.println("Usuario: ");
				usuarioIn = console.nextLine().toLowerCase();
			}while (usuarioIn == "");
			
			do {
				System.out.println("Contraseña: ");
				contrasenaIn = console.nextLine();
			}while (contrasenaIn == "");

			do {
				System.out.println("Clave Token: ");
				tokenIn = console.nextLine().toLowerCase();
			}while (tokenIn == "");
			
			Integer tokenInParseado = Integer.parseInt(tokenIn);
			
			loginExitoso = validarDatos(usuarioIn, contrasenaIn, tokenInParseado, token);
			
			intentosRestantes--;
			if(!loginExitoso) {
				if(intentosRestantes > 0) {
					System.out.println("Error de credenciales, desea intentarlo nuevamente? S/N: ");
					reintentar = console.nextLine().toUpperCase();
					if(reintentar.equals("N")) {
						System.out.println("Gracias por utilizar Online Banking.");
					}
				}else {
					System.out.println("Error de credenciales. Usuario bloqueado, por favor dirijase a la sucursal mas cercana.");
				}
			}else {
				System.out.println("Credenciales correctas, bienvenido a su Online Banking.");
				break;
			}
		}


	}

	public static void main(String[] args) {
		
		
		System.out.println("Bievenido a Online Banking, por favor ingrese las credenciales solicitadas: \n");
		
		inputDatos();
		
		

		
	}

}
