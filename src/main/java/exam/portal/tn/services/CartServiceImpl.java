package exam.portal.tn.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import exam.portal.tn.entities.Cart;
import exam.portal.tn.entities.User;
import exam.portal.tn.repository.CartRepository;
import exam.portal.tn.repository.UserRepository;




@Transactional
@Component
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Cart addCartToUser(Cart cart, long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		user.addCartToUser(cart);
		return cartRepository.save(cart);
	}

	@Override
	public void deleteCart(long id) {
		cartRepository.deleteById(id);
		
	}

	@Override
	public List<Cart> findCartsForUser(long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		return user.getCarts();
	}

	@Override
	public Cart findCartById(long id) {
		return cartRepository.findById(id).orElse(null);
	}

	@Override
	public void removeFromCart(long idCart, long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		Cart cart = cartRepository.findById(idCart).orElse(null);
		user.removeFromCart(cart);
		cartRepository.delete(cart);
	}

	@Override
	public Cart findByCartName(String name) {
		Optional<Cart> carts = cartRepository.findByName(name);
		if (carts.isPresent()) {
			Cart cart = carts.get();
			return cart;
		}
		return null;
	}

}
