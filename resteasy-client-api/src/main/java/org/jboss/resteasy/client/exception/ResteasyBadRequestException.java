package org.jboss.resteasy.client.exception;

import static org.jboss.resteasy.client.exception.WebApplicationExceptionWrapper.sanitize;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;

/**
 * Wraps a {@link BadRequestException} with a {@linkplain #sanitize(Response) sanitized} response.
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public class ResteasyBadRequestException extends BadRequestException implements WebApplicationExceptionWrapper<BadRequestException> {

   private static final long serialVersionUID = -6250430572164780061L;
   private final BadRequestException wrapped;
   private final Response sanitizedResponse;

    ResteasyBadRequestException(final BadRequestException wrapped) {
        super(wrapped.getMessage(), wrapped.getResponse(), wrapped.getCause());
        this.wrapped = wrapped;
        this.sanitizedResponse = sanitize(wrapped.getResponse());
    }

    @Override
    public BadRequestException unwrap() {
        return wrapped;
    }

    @Override
    public Response getSanitizedResponse() {
        return sanitizedResponse;
    }
}