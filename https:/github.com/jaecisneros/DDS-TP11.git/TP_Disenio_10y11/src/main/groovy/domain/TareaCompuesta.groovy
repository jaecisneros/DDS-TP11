package domain

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;

@Entity
@Table(name="Tarea")
class TareaCompuesta extends Tarea{

	@OneToMany
	private List<Tarea> tareas

	public TareaCompuesta(){
		super()
		this.tareas = new ArrayList<Tarea>();
	}

	@Override
	public def getCostoTotal(){
		def costo = this.tareas.sum{ t -> t.getCostoTotal() }

		if(this.tieneOverHead()){
			costo += costo * 0.04
		}
		return costo
	}

	private Boolean tieneOverHead(){
		return this.tareas.size() > 3
	}

	@Override
	public def setTiempo(def tiempo) {
	}

	@Override
	def getTiempoTotal(){
		return this.tareas.sum { t -> t.getTiempoTotal()}
	}

	@Override
	public def setComplejidad(Complejidad complejidad) {
	}

	@Override
	public def getMaxDiasDeAtraso() {
		return this.tareas.sum{ t -> t.getMaxDiasDeAtraso() }
	}

	@Override
	public def getCompletitud() {
		return this.tareas.sum{ t -> t.completitud }
	}

	@Override
	public void addTarea(Tarea tarea){
		this.tareas.add(tarea)
	}

	@Override
	public def getCantidadSubtareas(){
		this.tareas.size()
	}
}
