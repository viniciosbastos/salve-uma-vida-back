package br.com.wb.salvaumavida.resources

import br.com.wb.salvaumavida.models.Response
import br.com.wb.salvaumavida.services.MapService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MapResource (
        private val mapService: MapService
        ){

    @GetMapping("/map")
    fun getMap(
            @RequestParam("param", defaultValue = "") param: String
    ): Response {
        return try {
            Response.Success(mapService.getMapInfo())
        } catch (ex: Exception) {
            Response.Error(ex.message!!, ex.cause!!.toString())
        }
    }
}