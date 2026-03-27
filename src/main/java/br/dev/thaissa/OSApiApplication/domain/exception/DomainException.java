    
package br.dev.thaissa.OSApiApplication.domain.exception;

/**
 *
 * @author sesi3dib
 */
public class DomainException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public DomainException(String message) {
        super (message);
    }
    
}
