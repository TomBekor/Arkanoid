/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl the HitListener we want to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the HitListener we want to remove.
     */
    void removeHitListener(HitListener hl);
}