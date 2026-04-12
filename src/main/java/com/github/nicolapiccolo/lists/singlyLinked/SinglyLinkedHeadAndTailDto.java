package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Optional;

public record SinglyLinkedHeadAndTailDto<T>(Optional<SinglyLinkedListNode<T>> head, Optional<SinglyLinkedListNode<T>> tail) {
}
