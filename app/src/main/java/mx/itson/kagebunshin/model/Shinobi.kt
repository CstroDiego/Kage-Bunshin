package mx.itson.kagebunshin.model

import java.io.Serializable

class Shinobi : Serializable {
    var id: Int? = null
    var nombre: String? = null
    var clan: String? = null
    var aldea: String? = null
    var rango: String? = null
    var descripcion: String? = null
    var imagen: String? = null

    constructor(id: Int?, nombre: String?, clan: String?, aldea: String?, rango: String?, descripcion: String?, imagen: String?) {
        this.id = id
        this.nombre = nombre
        this.clan = clan
        this.aldea = aldea
        this.rango = rango
        this.descripcion = descripcion
        this.imagen = imagen
    }
}
