package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Optional;

public record SinglyLinkedHeadAndTailDto(Optional<SinglyLinkedListNode> head, Optional<SinglyLinkedListNode> tail) {
}
