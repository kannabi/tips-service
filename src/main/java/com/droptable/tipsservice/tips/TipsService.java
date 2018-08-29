package com.droptable.tipsservice.tips;

import com.droptable.tipsservice.dao.db.Tip;
import com.droptable.tipsservice.dao.db.Waiter;
import com.droptable.tipsservice.repositories.TipsRepository;
import com.droptable.tipsservice.repositories.WaitersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TipsService {

    private final TipsRepository tipsRepository;
    private final WaitersRepository waitersRepository;

    @Autowired
    public TipsService(TipsRepository tipsRepository, WaitersRepository waitersRepository) {
        this.tipsRepository = tipsRepository;
        this.waitersRepository = waitersRepository;
    }

    public void registerTips(String waiterId, BigDecimal sum, Long time) throws NoSuchElementException {
        Optional<Waiter> waiter = waitersRepository.findById(waiterId);
        if (!waiter.isPresent()) {
            throw new NoSuchElementException("There is now waiter with this id");
        }
        Tip save = tipsRepository.save(new Tip(time, sum, waiter.get()));
        System.out.println("kek");
    }

    @Scheduled(cron = "0 0 * * *")
    public void payTips() {
        Iterator<Tip> tipsIterator = tipsRepository.findAll().iterator();

        Tip tip;
        while (tipsIterator.hasNext()){
            tip = tipsIterator.next();
            pay(tip.getWaiter().getAccountBill());
            tipsRepository.deleteById(tip.getTime());
        }
    }

    private void pay(String accountBill) {
        //just an empty mock method
    }
}
