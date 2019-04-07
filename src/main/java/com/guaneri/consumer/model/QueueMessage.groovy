package com.guaneri.consumer.model

import groovy.transform.CompileStatic

@CompileStatic
class QueueMessage {

    FileMeta fileMeta

    static Builder getBuilder() {
        return new Builder()
    }

    static class Builder {

        QueueMessage queueMessage

        Builder() {
            queueMessage = new QueueMessage()
        }

        Builder fileMeta(FileMeta fileMeta) {
            queueMessage.setFileMeta(fileMeta)
            return this
        }

        QueueMessage build() {
            return queueMessage;
        }
    }
}
