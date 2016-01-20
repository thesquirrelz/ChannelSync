import groovyx.net.http.RESTClient
import groovyx.net.http.HttpResponseException
import static groovyx.net.http.ContentType.*
import groovy.json.*

class RestApi { 
  static String productGet(String url, String token, String path) {
      Map<String, Object> headers = ['X-Spree-Token': token]

      def spree = new RESTClient(url)

      def resp = spree.get(
          path: path,
          headers: headers,
          requestContentType : JSON 
       )

      List SpreeJson = new JsonBuilder(resp.data).toPrettyString()
      def f = new File('SpreeProduct.json')
      if (f) f.delete()
      f.append SpreeJson
   }

   static String productPut(String url, String token, String path) {
     def spree = new RESTClient(url)

     Map<String, Object> headers = ['X-Spree-Token': token]

     def newName = "Modified Name ${Math.abs(new Random().nextInt())}".toString()
     println "New name should equal: '$newName'"
     // try will execute the method no matter what finally should print the status  
     try {
        def request = spree.put(
           path: path,
           headers: headers,
           body: [name: newName],
           requestContentType: JSON
         )
        } finally {
            println("Success: $request.success")
            println("Status:  $request.status")
            println("Reason:  $request.statusLine.reasonPhrase")
            JSONObject userJson = JSON.parse(request.data)
            println("Content: \n${JsonOutput.prettyPrint(JsonOutput.toJson(request.data))}")
        }
   }

   static String productPost(String url, String token, String path) {
     def spree = new RESTClient(url)
     Map<String, Object> headers = ['X-Spree-Token': token]

     def newName = "Modified Name ${Math.abs(new Random().nextInt())}".toString()
     println "New name should equal: '$newName'"

     // try will execute the method no matter what finally should print the status  
      def request = spree.post(
         path: path,
         headers: headers,
         body: [name: newName, description: 'this is a new description', shipping_category_id: '1', designer_id: '2'],
         requestContentType: URLENC
       )
   }


   static String productPost(String url, String token, String body, String path) {
    Map<String, Object> headers = ['X-Spree-Token': token, 'Content-Type': 'application/x-www-form-urlencoded']
      try {
        def request = SpreeApi.post(
          path: path,
          requestContentType: URLENC,
          headers: headers, 
          body: body
       )
      } catch(HttpResponseException e) {
          String r = e.response
          println("Success: $r.success")
          println("Status:  $r.status")
          println("Reason:  $r.statusLine.reasonPhrase")
          println("Content: \n${JsonOutput.prettyPrint(JsonOutput.toJson(r.data))}")
      }
   }
}

postBody = ["product[name]": 'boombastic', "product[price]": "100", "product[shipping_category_id]": 2, "product[designer_id]": 1]
SpreeUrl = new RESTClient('https://staging.thesquirrelz.com')
apiKey = '10ae480e1d942040db7e99bb2bef8bf13bbc1714bd4d8e33'

productPath = '/products'
putPath = 'products/ruyi-necklace-fabric-flower.json'

url = 'https://staging.thesquirrelz.com/api/'
Spree = new SpreeApi()
Spree.productGet(url, apiKey, productPath)

//Spree.productPost(url, apiKey, postBody, productPath)
//Spree.productPut(url, apiKey, putPath)
//f.append JsonOutput.prettyPrint(resp.data)




