import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description "Obtiene un registro por su id=1"

  request {
    url "/ver/1"
    method GET()
  }

  response {
    status OK()
    headers {
      contentType applicationXml()
    }
    body """
      <Producto>
      <id>1</id>
      <nombre>Nissan</nombre>
      <precio>800.0</precio>
      <createAt>1394938800000</createAt>
      </Producto>
    """
  }
}