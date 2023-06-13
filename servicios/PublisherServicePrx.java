//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.8
//
// <auto-generated>
//
// Generated from file `CoffeMach.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package servicios;

public interface PublisherServicePrx extends com.zeroc.Ice.ObjectPrx
{
    default void attach(SubscriberServicePrx subscriberProxy)
    {
        attach(subscriberProxy, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void attach(SubscriberServicePrx subscriberProxy, java.util.Map<String, String> context)
    {
        _iceI_attachAsync(subscriberProxy, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> attachAsync(SubscriberServicePrx subscriberProxy)
    {
        return _iceI_attachAsync(subscriberProxy, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> attachAsync(SubscriberServicePrx subscriberProxy, java.util.Map<String, String> context)
    {
        return _iceI_attachAsync(subscriberProxy, context, false);
    }

    /**
     * @hidden
     * @param iceP_subscriberProxy -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_attachAsync(SubscriberServicePrx iceP_subscriberProxy, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "attach", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeProxy(iceP_subscriberProxy);
                 }, null);
        return f;
    }

    default void reportChange()
    {
        reportChange(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void reportChange(java.util.Map<String, String> context)
    {
        _iceI_reportChangeAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> reportChangeAsync()
    {
        return _iceI_reportChangeAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> reportChangeAsync(java.util.Map<String, String> context)
    {
        return _iceI_reportChangeAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_reportChangeAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "reportChange", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    default void getUpdate()
    {
        getUpdate(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void getUpdate(java.util.Map<String, String> context)
    {
        _iceI_getUpdateAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> getUpdateAsync()
    {
        return _iceI_getUpdateAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> getUpdateAsync(java.util.Map<String, String> context)
    {
        return _iceI_getUpdateAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_getUpdateAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getUpdate", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static PublisherServicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), PublisherServicePrx.class, _PublisherServicePrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static PublisherServicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), PublisherServicePrx.class, _PublisherServicePrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static PublisherServicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), PublisherServicePrx.class, _PublisherServicePrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static PublisherServicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), PublisherServicePrx.class, _PublisherServicePrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static PublisherServicePrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, PublisherServicePrx.class, _PublisherServicePrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static PublisherServicePrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, PublisherServicePrx.class, _PublisherServicePrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default PublisherServicePrx ice_context(java.util.Map<String, String> newContext)
    {
        return (PublisherServicePrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default PublisherServicePrx ice_adapterId(String newAdapterId)
    {
        return (PublisherServicePrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default PublisherServicePrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (PublisherServicePrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default PublisherServicePrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (PublisherServicePrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default PublisherServicePrx ice_invocationTimeout(int newTimeout)
    {
        return (PublisherServicePrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default PublisherServicePrx ice_connectionCached(boolean newCache)
    {
        return (PublisherServicePrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default PublisherServicePrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (PublisherServicePrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default PublisherServicePrx ice_secure(boolean b)
    {
        return (PublisherServicePrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default PublisherServicePrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (PublisherServicePrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default PublisherServicePrx ice_preferSecure(boolean b)
    {
        return (PublisherServicePrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default PublisherServicePrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (PublisherServicePrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default PublisherServicePrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (PublisherServicePrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default PublisherServicePrx ice_collocationOptimized(boolean b)
    {
        return (PublisherServicePrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default PublisherServicePrx ice_twoway()
    {
        return (PublisherServicePrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default PublisherServicePrx ice_oneway()
    {
        return (PublisherServicePrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default PublisherServicePrx ice_batchOneway()
    {
        return (PublisherServicePrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default PublisherServicePrx ice_datagram()
    {
        return (PublisherServicePrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default PublisherServicePrx ice_batchDatagram()
    {
        return (PublisherServicePrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default PublisherServicePrx ice_compress(boolean co)
    {
        return (PublisherServicePrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default PublisherServicePrx ice_timeout(int t)
    {
        return (PublisherServicePrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default PublisherServicePrx ice_connectionId(String connectionId)
    {
        return (PublisherServicePrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default PublisherServicePrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (PublisherServicePrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::servicios::PublisherService";
    }
}
