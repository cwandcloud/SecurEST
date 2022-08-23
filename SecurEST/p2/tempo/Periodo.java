package p2.tempo;

/**
 * Esta classe representa um periodo de tempo
 */
public class Periodo {

	private Hora inicio;
	private Hora fim;
	
	/**
	 * Criar um periodo indicando a hora inicial e final
	 * garantindo que a inicial e menor que a final
	 * @param ini hora inicial do periodo
	 * @param fim hora final do periodo
	 */
	public Periodo(Hora ini, Hora fim) {
		if( ini.compareTo(fim) > 0 ){
			this.inicio = fim;
			this.fim = ini;			
		}
		else {
			this.inicio = ini;
			this.fim = fim;
		}
	}	

	/**
	 * Criar um periodo indicando a hora inicial e a duracao em segundos
	 * @param ini hora inicial do periodo
	 * @param duracao duracao do periodo em segundos
	 */
	public Periodo(Hora ini, int duracao ) {
		this.inicio = ini;
		// tem de ser um clone senao na linha seguinte altera tambem a hora inicial
		fim = ini.clone();  
		fim.somaSegundos( Math.abs(duracao) );
	}	

	/**
	 * indica se uma dada hora esta dentro deste periodo de tempo
	 * @param h a hora que se pretende verificar se esta dentro do periodo de tempo
	 * @return true, se a hora esta dentro, false caso contrario
	 */
	public boolean estaDentro( Hora h ){
		return h.compareTo( inicio ) >= 0 && h.compareTo( fim ) <= 0;
	}
	
	/**
	 * Verifica se outro periodo de tempo interseta este periodo
	 * @param outro periodo com o qual se pretende verificar a intersecao
	 * @return true, se os periodos se intersetam 
	 */
	public boolean interseta( Periodo outro ){
		if( outro.fim.compareTo( inicio ) < 0 )
			return false;
		if( outro.inicio.compareTo( fim ) > 0 )
			return false;		
		return true;		
	}
	
	/**
	 * indica se o periodo esta englobado dentro do periodo p 
	 * Um periodo esta englobado noutro se estiver completamente dentro dele 
	 * @param p periodo a testar 
	 * @return true, se o periodo esta englobado em p  
	 */
	public boolean estaContido( Periodo p ){
		return p.inicio.compareTo( inicio ) <= 0 && p.fim.compareTo( fim ) >= 0;
	}
	
	/**
	 * indica se o periodo p esta englobado neste periodo
	 * Um periodo esta englobado noutro se estiver completamente dentro dele 
	 * @param p periodo a testar
	 * @return true, se p esta englobado no nosso periodo
	 */
	public boolean contem( Periodo p ){
		return p.estaContido( this );
	}
	
	
	/**
	 * Junta o periodo p a este. A juncao so e valida se os periodos se intersetarem,
	 * caso contrario, nao ha juncao
	 * @param p periodo a juntar
	 */
	public void junta( Periodo p ){
		if( !interseta(p))
			return;
		
		if( p.inicio.compareTo(inicio) < 0 )
			inicio = p.inicio.clone();
		if( p.fim.compareTo(fim) > 0 )
			fim = p.fim.clone();		
	}
	
	
	/**
	 * devolve a uniao deste periodo com o periodo p
	 * Se os periodos nao se intersetarem nao ha uniao. Se se intersetarem
	 * a uniao indica o maior periodo de tempo possivel juntando os dois
	 * @param p periodo a unir
	 * @return a uniao do periodo com o periodo p, null se nao se intersetarem
	 */
	public Periodo getUniao( Periodo p){
		if( !interseta( p ))
			return null;
		Hora i = p.inicio.compareTo(inicio) < 0? p.inicio.clone(): inicio.clone();
		Hora f = p.fim.compareTo(fim) > 0? p.fim.clone(): fim.clone();
		return new Periodo( i, f);
	}
	
	
	/**
	 * Devolve a intersecao do periodo com o periodo p, ou seja,
	 * qual o periodo de tempo que possuem em comum
	 * @param p periodo a intersetar
	 * @return o periodo de tempo que possuem em comum, null se nao se intersetarem
	 */
	public Periodo getInterseccao( Periodo p){
		if( !interseta(p) )
			return null;
		
		Hora i = p.inicio.compareTo(inicio) > 0? p.inicio.clone(): inicio.clone();
		Hora f = p.fim.compareTo(fim) < 0? p.fim.clone(): fim.clone();
		return new Periodo( i, f);
	}

	/**
	 * devolve a hora final
	 * @return a hora final
	 */
	public Hora getFim() {
		return fim;
	}

	/**
	 * altera a hora final
	 * @param fim a nova hora final
	 */
	public void setFim(Hora fim) {
		if( fim.compareTo( inicio) >= 0)
			this.fim = fim;
	}

	/**
	 * devolve a hora inicial
	 * @return a hora inicial
	 */
	public Hora getInicio() {
		return inicio;
	}

	/**
	 * altera a hora inicial
	 * @param ini a nova hora inicial
	 */
	public void setInicio(Hora ini) {
		if( ini.compareTo( fim ) <= 0)
			this.inicio = ini;
	}

	/**
	 * retorna o Periodo em forma de String
	 * @return o Periodo em forma de String
	 */
	public String toString(){
		return "[ " + inicio + " - " + fim + " ]";
	}
}
