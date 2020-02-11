// Assignment 1 Part 2: Solution

 class Delegation {
	
	public static void main(String args[]) {
		B b = new B();
		System.out.println(b.f()+b.g()-b.p(1)+b.q(2));
		
		B2 b2 = new B2();
		System.out.println(b2.f()+b2.g()-b2.p(1)+b2.q(2));
		
		D d = new D();
		System.out.println(d.f()+d.g()-d.h()+d.p(1)-d.q(2)+d.r());
		
		D2 d2 = new D2();
		System.out.println(d2.f()+d2.g()-d2.h()+d2.p(1)-d2.q(2)+d2.r());	
		
	}
}
 
 class Delegation2 {
		
	public static void main(String args[]) {
		
		E e = new E();
		System.out.println(e.f()-e.g()+e.h()-e.p(1)+e.q(2)-e.r()+e.k(100));
		
		E2 e2 = new E2();
		System.out.println(e2.f()-e2.g()+e2.h()-e2.p(1)+e2.q(2)-e2.r()+e2.k(100));

		F f = new F();
		System.out.println(f.f()+f.g()+f.h()+f.p(1)-f.q(2)-f.r()-f.j(10)+f.l(100));
		
		F2 f2 = new F2();
		System.out.println(f2.f()+f2.g()+f2.h()+f2.p(1)-f2.q(2)-f2.r()-f2.j(10)+f2.l(100));		
	}
}
 
 
abstract class A {
	int a1 = 100;
	int a2 = 200;

	public int f() {
		return a1 + p(100) + q(100);
	}

	protected abstract int p(int m);

	protected abstract int q(int m);
}

class B extends A {
	int b1 = 1000;
	int b2 = 2000;

	public int g() {
		return this.p(100) + this.q(200); 
	}

	public int p(int m) {
		return m + a1+b1;
	}

	public int q(int m) {
		return m + a2+b2;
	}
}

abstract class C extends B {
	int c1 = 10000;
	int c2 = 20000;

	public int r() {
		return f() + g() + h();
	}

	public int q(int m) {
		return m + a2 + b2 + c2;
	}

	protected abstract int h();
}

class D extends C {
	int d1 = 1000000;
	int d2 = 2000000;

	public int r() {
		return f() + g() + h();
	}

	public int p(int m) {
		return super.p(m) + d2;
	}

	public int h() {
		return a1 + b1 + c1;
	}
	
	public int j(int n) {
		return r() + super.r();
	}

}

class E extends C {
	int e1 = 1;
	int e2 = 2;

	public int q(int m) {
		return p(m) + c2;
	}

	public int h() {
		return a1 + b1 + e1;
	}
	
	public int k(int n) {
		return q(n) + super.q(n);
	}

}

class F extends D {
	int f1 = 10;
	int f2 = 20;

	public int q(int m) {
		return p(m) + c1 + d1;
	}

	public int h() {
		return c2 + f2;
	}
	
	public int l(int n) {
		return q(n) + super.q(n);
	}

}



// ============= TRANSLATION IN TERMS OF DELEGATION ===============

interface IA {
	int f();
	int p(int m);
	int q(int m);
}

interface IB extends IA {
	int g();
}

interface IC extends IB {
	int r();
	int h();
}

interface ID extends IC {
	int j(int n);
}

interface IE extends IC {
	int k(int n);
}

interface IF extends ID {
	int l(int n);
}


class A2 implements IA {
	int a1 = 100;
	int a2 = 200;
	IA this2;

	public A2(IA sub) {
		this2 = sub;
	}

	public int f() {
		return a1 + p(100) + q(100);
	}

	public int p(int m) { // over-ridden
		return this2.p(m);
	}

	public int q(int m) { // over-ridden
		return this2.q(m);
	}
}

class B2 implements IB {
	int b1 = 1000;
	int b2 = 2000;
	IB this2;
	A2 super2;

	public B2(IB sub) { // create superclass object
		this2 = sub;
		super2 = new A2(this2); // pass subclass object (for efficiency)
	}
	
	public B2() {
		super2 = new A2(this);
		this2 = this;
	}

	public int f() { // delegate call for f
		return super2.f();
	}

	public int g() { // define g, note that q was over-ridden
		return this2.p(100) + this2.q(200);
	}

	public int p(int m) { // define p but it was over-ridden in D
		return m + super2.a1+b1;
	}

	public int q(int m) { // define p but it was over-ridden by E and F
		return m + super2.a2+b2;
	}
}

class C2 implements IC {
	int c1 = 10000;
	int c2 = 20000;
	B2 super2;
	IC this2;

	public C2(IC o) {
		this2 = o;
		super2 = new B2(this2);
	}

	public int f() { // delegate call for f
		return super2.f();
	}

	public int g() { // delegate call for g
		return super2.g();
	}
	
	public int h() { // call sub-class method
		return this2.h();
	}

	public int p(int m) { // delegate call for p
		return super2.p(m);
	}

	public int q(int m) { // define q
		return m + super2.super2.a2 + super2.b2 + c2;
	}
	
	public int r() { // define r
		return f() + g() + h();
	}
}

class D2 implements ID {
	int d1 = 1000000;
	int d2 = 2000000;
	C2 super2;
	ID this2;

	public D2(ID o) {
		this2 = o;
		super2 = new C2(this2);
	}
	
	public D2() {
		super2 = new C2(this);
		this2 = this;
	}

	public int f() { // delegate call for f
		return super2.f();
	}

	public int g() { // delegate call for g
		return super2.g();
	}
	
	public int r() {
		return f() + g() + this2.h();
	}

	public int p(int m) {  
		return super2.p(m) + d2;
	}
	
	public int h() {
		return super2.super2.super2.a1 + super2.super2.b1 + super2.c1;
	}
	
	public int j(int n) {
		return r() + super2.r();
	}

	public int q(int m) { // delegate call for q
		return super2.q(m);
	}
	
}

class E2 implements IE {
	int e1 = 1;
	int e2 = 2;
	C2 super2;
	
	public E2() {
		super2 = new C2(this);
	}
	
	public int f() { // delegate call for f
		return super2.f();
	}

	public int g() { // delegate call for g
		return super2.g();
	}
	
	public int p(int m) { // delegate call for p
		return super2.p(m);
	}
	
	public int r() { // delegate call for r
		return super2.r();
	}
	
	public int q(int m) {
		return  p(m) + super2.c2;
	}

	public int h() {
		return super2.super2.super2.a1 + super2.super2.b1 +  e1;
	}

	public int k(int n) {
		return  q(n) + super2.q(n);
	}



}

class F2 implements IF{
	int f1 = 10;
	int f2 = 20;

	D2 super2;
	
	public F2() {
		super2 = new D2(this);
	}
	
	public int f() { // delegate call for f
		return super2.f();
	}

	public int g() { // delegate call for g
		return super2.g();
	}
	
	public int p(int m) { // delegate call for p
		return super2.p(m);
	}

	public int r() { // delegate call for r
		return super2.r();
	}
	
	public int j(int n) { // delegate call for j
		return super2.j(n);
	}
	
	public int q(int m) {
		return p(m) +  
				super2.super2.c1 + 
				super2.d1;
	}
	
	public int h() {
		return  super2.super2.c2 + f2;
	}
	
	public int l(int n) {
		return q(n) + super2.q(n);
	}

}
 