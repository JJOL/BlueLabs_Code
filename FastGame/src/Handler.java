import java.awt.Graphics;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

// Clase encargada de manejar a los objetos
public class Handler {
	// Se hace una lista de objetos
	/*
	 *  CopyOnWriteArrayList ayuda a que no importe que se intenten correr varios procesos
	 *  a la vez sobre éste, siempre podrá ser modificada la lista
	 */	
	public CopyOnWriteArrayList <GameObject> obj;

	// Constructor del Handler
	public Handler()
	{
		// Se instancia como una nueva lista de objetos
		obj = new CopyOnWriteArrayList <GameObject>();
	}
	
	// Método encargado de acutalizar los objetos contenidos en el Handler
	public void tick()
	{
		// Se hace un iterador de la lista (se puede hacer con un for each)
		ListIterator <GameObject> iterator = obj.listIterator();
		// Se actualizan todos los objetos
		while (iterator.hasNext())
		{
			GameObject aux = iterator.next();
			aux.tick();
		}
	}
	// Método encargado de renderizar los objetos contenidos en el Hanlder
	public void render(Graphics g)
	{
		// Se hace un iterador de la lista (se puede hacer con un for each)
		ListIterator <GameObject> iterator = obj.listIterator();
		// Se renderizan todos los objetos
		while (iterator.hasNext())
		{
			GameObject aux = iterator.next();
			aux.paint(g);
		}
	}
	// Método para añadirle objetos al Handler
	public void addObj(GameObject obj)
	{
		// Le añade el objeto a la lista
		this.obj.add(obj);
	}
	// Método para remover los objetos del Handler
	public void removeObj(GameObject obj)
	{
		// Le remueve el objeto a la lista
		this.obj.remove(obj);
	}
}
