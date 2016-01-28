/*
 * This software is in the public domain under CC0 1.0 Universal plus a
 * Grant of Patent License.
 *
 * To the extent possible under law, the author(s) have dedicated all
 * copyright and related and neighboring rights to this software to the
 * public domain worldwide. This software is distributed without any
 * warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication
 * along with this software (see the LICENSE.md file). If not, see
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpEntity
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpGet
import org.apache.http.entity.ContentType
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

Logger logger = LoggerFactory.getLogger("SpreeGetProducts")
logger.info("======== Get Spree Products")
//logger.warn("======== JSON remote service request to channelUrl: ${channelUrl}, endPoint: ${endPoint}, apiKey: ${apiKey}")

def location = channelUrl + endPoint + apiKey
// logger.warn("======== location: ${location}")

CloseableHttpClient httpClient = HttpClients.createDefault()
try {
    HttpGet httpGet = new HttpGet(location)
        httpGet.addHeader("Accept" , "application/json")

    CloseableHttpResponse response = httpClient.execute(httpGet)
        // logger.warn("======== httpGet: ${httpGet}")

        HttpEntity entity = response.getEntity()
        // logger.warn("======== entity: ${entity}")

        String responseString = EntityUtils.toString(entity, "UTF-8");
        logger.warn("======== responseString: ${responseString}")
} finally {
    httpClient.close()
}
