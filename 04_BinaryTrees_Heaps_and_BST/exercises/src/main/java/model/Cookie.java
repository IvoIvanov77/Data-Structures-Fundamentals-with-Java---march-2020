package model;

import interfaces.Decrease;

import java.util.Objects;

public class Cookie implements Comparable<Cookie>, Decrease<Cookie> {

    private int sweetness;

    public Cookie(int sweetness) {
        this.sweetness = sweetness;
    }

    public int getSweetness() {
        return sweetness;
    }

    @Override
    public void decrease() {
        this.sweetness --;
    }

    @Override
    public int compareTo(Cookie cookie) {
        return this.sweetness - cookie.sweetness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cookie cookie = (Cookie) o;
        return sweetness == cookie.sweetness;
    }

    @Override
    public int hashCode() {
        final int prime = 73;
        return Objects.hash(sweetness) * prime;
    }
}
