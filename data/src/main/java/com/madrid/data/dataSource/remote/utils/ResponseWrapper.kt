package com.madrid.data.dataSource.remote.utils

//suspend inline fun <reified T > responseWrapper (
//    client: HttpClient,
//    response : HttpClient.()->HttpResponse,
//):T {
//    try {
//        return client.response().body()
//    } catch (e: ClientRequestException) {
//        when (e) {
//            403 -> throw Resources.NotFoundException()
//            404 -> throw PermisionDeniedException()
//        else -> throw UnknownError()
//        }
//    }
//    catch (e: RedirectResponseException) {}
//    catch (e: ServerResponseException) {}
//    catch (e:IOException){}
//    catch (e: Exception) {}
//}
