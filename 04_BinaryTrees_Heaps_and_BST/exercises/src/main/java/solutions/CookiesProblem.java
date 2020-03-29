package solutions;

import model.Cookie;

public class CookiesProblem {
    public Integer solve(int k, int[] cookies) {
        MinHeap<Cookie> cookiesHeap = new MinHeap<>();
        for (int i : cookies) {
            cookiesHeap.add(new Cookie(i));
        }
        int counter = 0;
        while (cookiesHeap.peek().getSweetness() <= k) {
            Cookie firstCookie;
            Cookie secondCookie;
            try {
                firstCookie = cookiesHeap.poll();
                secondCookie = cookiesHeap.poll();
            } catch (IllegalStateException e) {
                return -1;
            }
            Cookie newCookie = new Cookie(firstCookie.getSweetness() + 2 * secondCookie.getSweetness());
            cookiesHeap.add(newCookie);
            counter ++;
        }
        return counter;
    }


}
