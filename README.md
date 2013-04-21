## Streamer
Streamer is a library for converting collections to streams lazily

## WHY???
check out the note on toStream.  "Will not terminate for infinite collections".  an eagerly evaluated conversion to streams is crazy.

## Can I reuse my iterator after I've converted it?
nope

## Can I reuse my iterable after I've converted it?
yep, assuming your iterator doesn't mutate

## Can I use this on traversables?
ugh no just implement iterable

## api?
ok sure

### direct
```scala
import com.mosesn.streamer.Streamer
val x = (0 until 10).toList
val y = Streamer(x)
```

### implicits
```scala
import com.mosesn.streamer.StreamConverters._
val x = (0 until 10).toList
val y = x.toLazyStream
```

### why aren't there implicits that will turn my iterables/iterators into streams for me?
pretty sure this is an anti-pattern.  you'll use StreamConverters and you'll like it.

### why isn't X supported?
file a github issue.  I probably just didn't think of it.

## hasn't library X done this before?
probably.  this is almost no code.  this was mostly a learning exercise for scala's stream api.

## where can download the dependencies?
publishing to sonatype is a pain, so I haven't done it yet.  if you want to use it, file a github
issue and I'll work something out.

## found a bug
file a github issue
