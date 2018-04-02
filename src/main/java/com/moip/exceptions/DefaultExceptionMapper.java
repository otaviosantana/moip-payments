package com.moip.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class DefaultExceptionMapper {

//    @ExceptionHandler(ApplicationException.class)
//    public ResponseEntity<Object> handleAPIUnprocessableEntityException(Application exception) {
//        return APIUnprocessableEntityExceptionMapper.mapToResponseEntity(exception);
//    }
//
//    @Override
//    public Response toResponse(final Exception ex) {
//        final ApplicationException apex = toApplicationException(ex);
//        final ErrorResponse errorResponse = new ErrorResponse(String.valueOf(apex.getCode()), apex.getData());
//
//        if(logger.isInfoEnabled()) {
//            logger.info("Returning error (status: {}): {}.", //
//                            apex.getCode().getHttpStatus(), //
//                                errorResponse.toString());
//        }
//
//        if(apex instanceof SystemException) {
//            logger.error("System error.", apex);
//        }
//
//        return Response.status(apex.getCode().getHttpStatus())
//                .entity(errorResponse)
//                .type("application/json")
//                .build();
//    }
//
//    // Convert any unknown exception to ApplicationException to handler it after in an uniform interface.
//    private ApplicationException toApplicationException(final Exception ex) {
//        if(ex instanceof ApplicationException) {
//            return (ApplicationException) ex; // just return itself.
//        } else if(ex instanceof HystrixRuntimeException) {
//            return hystrixExceptionToApplicationException((HystrixRuntimeException)ex);
//        } else if(ex instanceof IllegalArgumentException) {
//            return new UserException(UserErrorCode.BAD_REQUEST, ex)
//                            .set("message", ex.getMessage());
//        } else if(ex instanceof ConstraintViolationException) {
//            final ConstraintViolationException cve = (ConstraintViolationException) ex;
//            return new UserException(UserErrorCode.INVALID_DATA, cve)
//                            .set("message", cve.getMessage())
//                            .set("errors", mapViolations(cve.getConstraintViolations()));
//        } else if(ex instanceof JsonProcessingException) {
//            return new UserException(UserErrorCode.BAD_REQUEST, ex)
//                            .set("message", ex.getMessage());
//        } else if(ex instanceof WebApplicationException){
//            if(ex instanceof NotFoundException) {
//                return new UserException(UserErrorCode.NOT_FOUND, ex);
//            } else {
//                return new UserException(UserErrorCode.BAD_REQUEST, ex)
//                                .set("message", ex.getMessage());
//            }
//        } else {
//            return new SystemException(SystemErrorCode.INTERNAL_SERVER_ERROR, ex)
//                            .set("message", ex.getMessage());
//        }
//    }
//
//    private ApplicationException hystrixExceptionToApplicationException(final HystrixRuntimeException ex) {
//        if(FailureType.SHORTCIRCUIT.equals(ex.getFailureType())                      ||
//                FailureType.REJECTED_THREAD_EXECUTION.equals(ex.getFailureType())    ||
//                FailureType.REJECTED_SEMAPHORE_EXECUTION.equals(ex.getFailureType()) ||
//                FailureType.REJECTED_SEMAPHORE_FALLBACK.equals(ex.getFailureType())) {
//            return new SystemException(SystemErrorCode.SERVICE_UNAVAILABLE, ex)
//                            .set("message", ex.getMessage());
//        } else if(FailureType.TIMEOUT.equals(ex.getFailureType())) {
//            return new SystemException(SystemErrorCode.GATEWAY_TIMEOUT)
//                            .set("message", ex.getMessage());
//        } else {
//            // lets check if was a ApplicationException inside of a command.
//            if(ex.getCause() instanceof ApplicationException) {
//                return (ApplicationException)ex.getCause();
//            } else {
//                return new SystemException(SystemErrorCode.BAD_GATEWAY, ex)
//                            .set("message", ex.getMessage());
//            }
//        }
//    }
//
//    /*
//     * Utility to convert Set<ConstraintViolation> to a simple map of field and errors.
//     *
//     */
//    private Map<String, String> mapViolations(final Set<ConstraintViolation<?>> constraintViolations) {
//        final Map<String, String> simpleViolations = new HashMap<String, String>();
//        constraintViolations
//            .stream()
//            .forEach(v -> simpleViolations.put(v.getPropertyPath().toString(), v.getMessage()));
//        return simpleViolations;
//    }
}
