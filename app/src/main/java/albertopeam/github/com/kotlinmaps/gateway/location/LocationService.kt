package albertopeam.github.com.kotlinmaps.gateway.location

/**
 * Created by alberto.penas.amor on 3/2/18.
 */
interface LocationClient {
    /**
     * Start the gps client
     * @return true if already connected
     * @throws LocationConnectionException if cannot connect, this exception return a ref to the message
     */
    @Throws(LocationConnectionException::class)
    fun start(): Boolean
    /**
     * Stop the gps client
     * @return true if disconnected
     */
    fun stop(): Boolean
    /**
     * Get current location
     * @return current location if avaliable
     * @throws NullPointerException not available
     * @throws IllegalStateException not connected, failed to connect
     * @throws SecurityException not have enough permissions
     * @throws IllegalArgumentException invalid api client
     */
    @Throws(NullPointerException::class, IllegalStateException::class, SecurityException::class, IllegalArgumentException::class)
    fun currentLocation(): Location
}