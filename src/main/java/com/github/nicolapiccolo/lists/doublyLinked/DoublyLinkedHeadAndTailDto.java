package com.github.nicolapiccolo.lists.doublyLinked;

import java.util.Optional;

public record DoublyLinkedHeadAndTailDto<T>(Optional<DoublyLinkedListNode<T>> head, Optional<DoublyLinkedListNode<T>> tail) {
}
