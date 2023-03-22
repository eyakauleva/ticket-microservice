package com.solvd.micro9.tickets.service.impl;

import com.solvd.micro9.tickets.persistence.DatabaseSequence;
import com.solvd.micro9.tickets.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
@RequiredArgsConstructor
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {

    private final ReactiveMongoOperations mongoOperations;

    @Override
    public Long generateSequence(final String sequenceName) throws InterruptedException, ExecutionException {
        return mongoOperations.findAndModify(new Query(Criteria.where("_id").is(sequenceName)),
                new Update().inc("sequence", 1), options().returnNew(true).upsert(true), DatabaseSequence.class)
                .toFuture().get().getSequence();
    }

}
