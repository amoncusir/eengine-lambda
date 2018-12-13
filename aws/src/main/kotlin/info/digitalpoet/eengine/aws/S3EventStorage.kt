package info.digitalpoet.eengine.aws

import com.fasterxml.jackson.databind.ObjectMapper
import info.digitalpoet.eengine.core.ConfigurationProperties
import info.digitalpoet.eengine.core.event.Event
import info.digitalpoet.eengine.core.storage.EventStorage
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectAclRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.ByteArrayInputStream
import java.io.InputStream

/** <!-- Documentation for: info.digitalpoet.eengine.aws.S3EventStorage on 12/12/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class S3EventStorage(
    private val s3: S3Client,
    private val properties: ConfigurationProperties,
    private val mapper: ObjectMapper
):
    EventStorage
{
    //~ Constants ======================================================================================================

    companion object
    {
        const val PROPERTIES_BUCKET_NAME = "aws.s3.bucket.event-store"
    }

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun saveEvent(event: Event)
    {
        val stream = parseEventToString(event)

        s3.putObject(PutObjectRequest.builder()
                         .bucket(properties[PROPERTIES_BUCKET_NAME])
                         .key(event.id)
//                         .tagging()
                         .build(),
                     RequestBody.fromBytes(stream.readBytes()))
    }

    override fun findEvent(eventId: String): Event
    {
        val stream = s3.getObject {
            GetObjectAclRequest.builder()
                .bucket(properties[PROPERTIES_BUCKET_NAME])
                .key(eventId)
                .build()
        }

        return parseStringToEvent(stream)
    }

    protected open fun parseEventToString(event: Event): InputStream
    {
        return ByteArrayInputStream(mapper.writeValueAsBytes(event))
    }

    protected open fun parseStringToEvent(stream: InputStream): Event
    {
        return mapper.readValue(stream, Event::class.java)
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
